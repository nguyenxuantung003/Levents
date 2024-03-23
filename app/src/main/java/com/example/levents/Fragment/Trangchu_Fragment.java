package com.example.levents.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.levents.Adapter.Fragment_Trangchu_Adapter;
import com.example.levents.Adapter.Sanpham_intrangchu_Adapter;
import com.example.levents.Adapter.Sanphammoi_intrangchu_Adapter;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.OnAddToCart;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Sanpham;
import com.example.levents.databinding.FragmentTrangchuBinding;

import java.util.ArrayList;

public class Trangchu_Fragment extends Fragment {
    FragmentTrangchuBinding binding;
    View view;
    ArrayList<Sanpham> sanphams;
    ArrayList<Sanpham> listdem;
    ArrayList<Sanpham> listsapxep;
    Sanpham_DAO sanphamDao;
    Fragment_Trangchu_Adapter fragmentTrangchuAdapter;
    private boolean hasMatchingProducts = true;
    private Sanphammoi_intrangchu_Adapter sanphammoiIntrangchuAdapter;
    private Sanpham_intrangchu_Adapter sanpham_intrangchu_adapter;



    public Trangchu_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrangchuBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        SharedPreferences preferences = getActivity().getSharedPreferences("KHACHHANG", Context.MODE_PRIVATE);
        String hoten = preferences.getString("hoten", "");
        sanphamDao = new Sanpham_DAO(getContext());
        sanphams = sanphamDao.getsanphamall();
        listdem = sanphamDao.getsanphamall();
        listsapxep = sanphamDao.getsanphamallSapXep();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rcvtrangchu.setLayoutManager(gridLayoutManager);
        binding.rcvNamngang.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        sanphammoiIntrangchuAdapter = new Sanphammoi_intrangchu_Adapter(listsapxep,getContext());
        sanpham_intrangchu_adapter = new Sanpham_intrangchu_Adapter(sanphams,getContext());
        binding.rcvtrangchu.setAdapter(sanpham_intrangchu_adapter);
        binding.rcvNamngang.setAdapter(sanphammoiIntrangchuAdapter);
        sanphammoiIntrangchuAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {

            }
        });
        binding.edtimKiem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        binding.edtimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sanpham_intrangchu_adapter.notifyDataSetChanged();
        sanpham_intrangchu_adapter.setOnAddToCartClickListenerTrangChu(new OnAddToCart() {
            @Override
            public void onAddToCartClick(Sanpham sanpham) {
                //themVaoGio(sanPham);
            }
        });
        sanpham_intrangchu_adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {
                //showDialogChiTietSanPham(adapter.getViTriSp(position));
            }
        });

        return view;
    }
   /* private Runnable sildeRunnable = new Runnable() {
        @Override
        public void run() {
//            binding.viewpage.setCurrentItem(binding.viewpage.getCurrentItem() + 1);
            int vitri = binding.viewpage.getCurrentItem();
            if (vitri == slidelist.size() - 1) {
                binding.viewpage.setCurrentItem(0);
            } else {
                binding.viewpage.setCurrentItem(vitri + 1);
            }
        }
    };
    */
    private int getSoLuongSp(int maSanPham) {
        for (Sanpham sanPham : sanphams) {
            if (sanPham.getMasanpham() == maSanPham) {
                return sanPham.getSoluong();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy sản phẩm
    }
}
     /* private void themVaoGio(SanPham sanPham) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("mataikhoan", 0);
        int maSanPham = sanPham.getMasanpham();
        int slSanPham = getSoLuongSp(maSanPham);
        gioHangArrayList = gioHangDao.getDanhSachGioHangByMaNguoiDung(mand);

        boolean isProductInCart = false;

        for (GioHang gioHang : gioHangArrayList) {
            if (gioHang.getMaSanPham() == maSanPham) {
                isProductInCart = true;
                if (gioHang.getSoLuongMua() < slSanPham) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    gioHangDao.updateGioHang(gioHang);
                    Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        if (!isProductInCart) {
            if (slSanPham > 0) {
                gioHangDao.insertGioHang(new GioHang(maSanPham, mand, 1));
            } else {
                Toast.makeText(getActivity(), "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            }
        }
    } */
    /* private void updateText() {
         if (!hasMatchingProducts) {
             binding.tenkoquantrong.setText("Sản phẩm không có trong giỏ hàng.");
             binding.tenkoquantrong.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.mau_hong));
             binding.nen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.mau_hong));

         } else {
             binding.tenkoquantrong.setText("Sản phẩm ");

         }
     } */

           /* private void showDialogChiTietSanPham(SanPham sanPham) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogChiTietSanPhamBinding chiTietSanPhamBinding = DialogChiTietSanPhamBinding.inflate(getLayoutInflater());
        dialog.setContentView(chiTietSanPhamBinding.getRoot());

        if (sanPham != null) {
            chiTietSanPhamBinding.txtMaSanPham.setText("Mã: " + String.valueOf(sanPham.getMasanpham()));
            chiTietSanPhamBinding.txtTenSanPham.setText("Tên:" + sanPham.getTensanpham());
            chiTietSanPhamBinding.txtGiaSanPham.setText("Giá: " + String.valueOf(sanPham.getGia()));
            chiTietSanPhamBinding.txtLoaiSanPham.setText("Loại sản phẩm: " + sanPham.getTenloaisanpham());
            chiTietSanPhamBinding.txtSoLuotBan.setText("Số lượt bán: 200");
            chiTietSanPhamBinding.txtMoTa.setText("Mô tả: " + sanPham.getMota());


        }
        if (sanPham.getSoluong() == 0) {
            chiTietSanPhamBinding.btnThemVaoGio.setVisibility(View.GONE);
            chiTietSanPhamBinding.txtHetHang.setVisibility(View.VISIBLE);
        } else {
            chiTietSanPhamBinding.btnThemVaoGio.setVisibility(View.VISIBLE);
            chiTietSanPhamBinding.txtHetHang.setVisibility(View.GONE);
        }
        chiTietSanPhamBinding.btnDongDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        chiTietSanPhamBinding.btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themVaoGio(sanPham);
//                Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "Đã cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    */
