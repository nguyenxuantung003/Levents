package com.example.levents.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.Giohang_Adapter;
import com.example.levents.Adapter.Hoadon_Adapter;
import com.example.levents.Adapter.Swipe;
import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.DAO.Donhang_DAO;
import com.example.levents.DAO.Giohang_DAO;
import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Giohang;
import com.example.levents.Model.Hoadon;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.Model.Sanpham;
import com.example.levents.databinding.DialogConfilmThanhToanBinding;
import com.example.levents.databinding.FragmentGiohangBinding;
import com.google.android.material.snackbar.Snackbar;
import com.example.levents.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Giohang_Fragment extends Fragment implements Giohang_Adapter.TotalPriceListener {
    private ArrayList<Giohang> list = new ArrayList<>();
    private Giohang_Adapter giohangAdapter;
    FragmentGiohangBinding binding;
    View view;
    Giohang_DAO giohangDao;
    Donhang_DAO donhangDao;
    Hoadon_Adapter hoadonAdapter;
    private QLhoadon_Fragment qLhoadonFragment;
    private ArrayList<Hoadon> listHoadon = new ArrayList<>();
    private Chitiethoadon_DAO chitiethoadonDao;
    private ArrayList<Sanpham> listSanpham = new ArrayList<>();
    private Sanpham_DAO sanphamDao;

    public Giohang_Fragment() {
    }
    private void displayCart(ArrayList<Giohang> cartList) {
        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        //
        if (giohangAdapter == null) {
            giohangAdapter= new Giohang_Adapter(getContext(), cartList);
            rcv.setAdapter(giohangAdapter);
        } else {
            giohangAdapter.updateCartList(cartList);
            giohangAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentGiohangBinding.inflate(inflater, container, false);
       view = binding.getRoot();
        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        giohangAdapter = new Giohang_Adapter(getContext(),list);
        rcv.setAdapter(giohangAdapter);
        giohangDao = new Giohang_DAO(getActivity());
        ItemTouchHelper sw = new ItemTouchHelper(new Swipe(giohangAdapter));
        sw.attachToRecyclerView(rcv);
        giohangAdapter.setTotalPriceListener(this);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("makhachhang", 0);
        chitiethoadonDao = new Chitiethoadon_DAO(getContext());
        donhangDao = new Donhang_DAO(getContext());
        sanphamDao = new Sanpham_DAO(getContext());
        list = giohangDao.getDanhSachGioHangByMaNguoiDung(mand);
        displayCart(list);
        binding.btnThanhToan.setOnClickListener(view -> {
           // showDialogThanhToan();

        });
        return view;
    }
    @Override
    public void onTotalPriceUpdated(int totalAmount) {
        if (binding != null && binding.txtTongTienThanhToan != null) {
            binding.txtTongTienThanhToan.setText(String.valueOf(totalAmount));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showDialogThanhToan() {
        //Mở dialog xác nhận
        LayoutInflater layoutInflater = getLayoutInflater();
        DialogConfilmThanhToanBinding thanhToanBinding = DialogConfilmThanhToanBinding.inflate(layoutInflater);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(thanhToanBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();

        //Thực hiện chức năng thanh toán ấn nút
        thanhToanBinding.btnThanhToan.setOnClickListener(view -> {

            //kiểm tra số lượng cuả từng sản phẩm được chọn khi click thanh toán
            for (Giohang gioHang : list) {
                if (gioHang.getSoLuong() == 0) {
                    Toast.makeText(getContext(), "Sản phẩm " + gioHang.getTenSanPham() + " đã hết hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // Ép kiểu tổng tiền thành kiểu Integer
            int totalAmount = Integer.parseInt(binding.txtTongTienThanhToan.getText().toString());
            // lấy ra thông tin người dùng đã được lưu trong sharedPreferences
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
            //Lấy ra mã người dùng
            int mand = sharedPreferences.getInt("mataikhoan", 0);
            //Lấy ra số tiền hiện có trong tài khoản của người dùng
            int tienHienCo = sharedPreferences.getInt("sotien", 0);

            //Lấy ra ngày tháng năm hiện tại
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngayHienTai = currentDate.format(formatter);

            //Kiểm tra số tiền hiện có trong tài khoản của người dùng có đủ để thanh toán hay không
            if (tienHienCo >= totalAmount) {

                //nếu tiền trong tài khoản đủ thì sẽ thực hiện trừ tiền
                int soTienConLai = tienHienCo - totalAmount;
                Khachang_DAO nguoiDungDao = new Khachang_DAO(getContext());

                //Update lại tiền trong tài khoản của người dùng
                if (nguoiDungDao.updateSoTien(mand, soTienConLai)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // put lại số tiền còn lại lên SharedPreferences
                    editor.putInt("sotien", soTienConLai);
                    editor.apply();
                    // khởi tạo đơn hàng mới khi đã lấy ra được thông tin cần thiết
                    // mặc định trạng thái khi thanh toán thành công là chờ phê duyệt
                    Hoadon donHang = new Hoadon();
                    //insert đơn hàng vào database
                    int orderId = donhangDao.insertDonHang(donHang);
                    if (orderId != 0) {
                        listHoadon.clear();
                        listHoadon.addAll(donhangDao.getDsDonHang());

                        //Kiểm tra xem đã có item nào được chọn hay chưa
                        boolean ktraItemDuocChon = false;
                        for (Giohang gioHang : list) {
                            if (gioHang.isSelected()) {
                                ktraItemDuocChon = true;
                                break;
                            }
                        }
                        //nếu đã có item được chọn để thanh toán thì thực hiện tiếp công việc
                        if (ktraItemDuocChon) {
                            // duyệt qua list gioHang và kiểm tra xem những item nào được chọn
                            for (Giohang gioHang : list) {
                                if (gioHang.isSelected()) {
                                    // lấy ra những sản phẩm được chọn để thanh toán
                                    Sanpham_DAO sanPhamDao = new Sanpham_DAO(getContext());
                                    Sanpham sanPham = sanPhamDao.getSanPhamById(gioHang.getMaSanPham());
                                    if (sanPham != null) {
                                        //thực hiện tạo chi tiết đơn hàng bằng những item giỏ hàng đã được chọn
                                        Hoadonchitiet chiTietDonHan = new Hoadonchitiet(orderId, gioHang.getMaSanPham(), gioHang.getSoLuongMua(), sanPham.getGia(), gioHang.getSoLuongMua() * sanPham.getGia());
                                        chitiethoadonDao.insertDonHangChiTiet(chiTietDonHan);
                                    } else {
                                        Toast.makeText(getContext(), "Sản phẩm không tìm thấy trong cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Vui lòng chọn sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                            return;
                        }



                        // Cập nhật số lượng sản phẩm sau khi thanh toán thành công
                        for (Giohang gioHang : list) {
                            int newQuantity = gioHang.getSoLuong() - gioHang.getSoLuongMua();
                            int newSoLuongBanRa = gioHang.getSoluongbanra() + gioHang.getSoLuongMua();
                            if (newQuantity < 0) {
                                Toast.makeText(getContext(), "Sản phẩm " + gioHang.getTenSanPham() + " không đủ số lượng trong kho", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            sanphamDao.updateSlSanPham(gioHang.getMaSanPham(), newQuantity,newSoLuongBanRa);

                        }
                        //khi thanh toán thành công thì xóa những item đc chọn
                        for (Giohang selected : list) {
                            if (selected.isSelected()) {
                                giohangDao.deleteGioHang(selected);
                            }
                        }

                        binding.txtTongTienThanhToan.setText(String.valueOf(0));
                        list = giohangDao.getDSGioHang();
                        giohangAdapter.updateCartList(list);
                        displayCart(list);

                        Snackbar.make(getView(), "Thanh toán thành công", Snackbar.LENGTH_SHORT).show();

                        //Sau khi thanh toán thành công
                        // Set mã đơn hàng vừa tạo ở trên và chuyển qua màn hình frgConfilmThanhToan
                        // để hiển thị những đơn hàng chi tiết mà người dùng vừa tạo
                        Bundle bundle = new Bundle();
                        bundle.putInt("maDonHang", orderId);

                        Confilmthanhtoan_Fragment frgConfilmThanhToan = new Confilmthanhtoan_Fragment();
                        frgConfilmThanhToan.setArguments(bundle);
                        FragmentManager fragmentManager = getParentFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.framelayout_trangchu, frgConfilmThanhToan);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thất bại khi thêm đơn hàng!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Thất bại khi cập nhật tài khoản!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Số tiền trong tài khoản không đủ!", Toast.LENGTH_SHORT).show();
            }
        });
        thanhToanBinding.btnThoat.setOnClickListener(view -> dialog.dismiss());

    }
}