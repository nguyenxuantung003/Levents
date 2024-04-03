package com.example.levents.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.levents.Activity.ProfileActivity;
import com.example.levents.Adapter.Fragment_Trangchu_Adapter;
import com.example.levents.Adapter.Giohang_Adapter;
import com.example.levents.Adapter.Sanpham_intrangchu_Adapter;
import com.example.levents.Adapter.Sanphammoi_intrangchu_Adapter;
import com.example.levents.DAO.Giohang_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.OnAddToCart;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Giohang;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import com.example.levents.databinding.DialogChitietsanphamBinding;
import com.example.levents.databinding.FragmentTrangchuBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

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
    private ArrayList<Giohang> gioHangArrayList = new ArrayList<>();
    private Giohang_DAO giohangDao;
    private Giohang_Adapter giohangAdapter;


    public Trangchu_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrangchuBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        SharedPreferences preferences = getActivity().getSharedPreferences("KHACHHANG", Context.MODE_PRIVATE);
        String hoten = preferences.getString("hoten", "");
        giohangDao = new Giohang_DAO(getActivity());
        gioHangArrayList = giohangDao.getDSGioHang();
        giohangAdapter = new Giohang_Adapter(getActivity(), gioHangArrayList);
        sanphamDao = new Sanpham_DAO(getContext());
        sanphams = sanphamDao.getsanphamall();
        listdem = sanphamDao.getsanphamall();
        listsapxep = sanphamDao.getsanphamallSapXep();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rcvtrangchu.setLayoutManager(gridLayoutManager);
        binding.rcvNamngang.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        sanphammoiIntrangchuAdapter = new Sanphammoi_intrangchu_Adapter(listsapxep, getContext());
        sanpham_intrangchu_adapter = new Sanpham_intrangchu_Adapter(sanphams, getContext());
        binding.rcvtrangchu.setAdapter(sanpham_intrangchu_adapter);
        binding.rcvNamngang.setAdapter(sanphammoiIntrangchuAdapter);
        binding.imaprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        binding.edtimKiem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    binding.viewpage.setVisibility(View.GONE);
                    binding.ten.setVisibility(View.GONE);
                    binding.rcvNamngang.setVisibility(View.GONE);
                    binding.khoangcach2.setVisibility(View.GONE);
                    binding.khoangcach1.setVisibility(View.GONE);
                }
            }
        });
        binding.edtimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase(); // Chuyển đổi sang chữ thường
                if (searchText.isEmpty()) {
                    hasMatchingProducts = true;
                    binding.viewpage.setVisibility(View.GONE);
                    binding.rcvtrangchu.setVisibility(View.VISIBLE);
                    binding.ten.setVisibility(View.GONE);
                    binding.rcvNamngang.setVisibility(View.GONE);
                    binding.khoangcach1.setVisibility(View.GONE);
                    binding.khoangcach2.setVisibility(View.GONE);
                    binding.tenkoquantrong.setText("Sản phẩm ");
//                    binding.nen.setVisibility(View.VISIBLE);
                    sanphams.clear();
                    sanphams.addAll(listdem);
                    sanpham_intrangchu_adapter.notifyDataSetChanged();
                } else {
                    binding.viewpage.setVisibility(View.GONE);
                    binding.khoangcach1.setVisibility(View.GONE);
                    binding.khoangcach2.setVisibility(View.GONE);
                    binding.tenkoquantrong.setText("Sản phẩm không có trong giỏ hàng");
                    sanphams.clear();
                    for (Sanpham sp : listdem) {
                        if (sp.getTensanpham().toLowerCase().contains(searchText)) {
                            sanphams.add(sp);
                        }
                    }
                    if (sanphams.isEmpty()) {
                        hasMatchingProducts = false;
                    } else {
                        hasMatchingProducts = true;
                    }
                    sanpham_intrangchu_adapter.notifyDataSetChanged();
                }
                updateText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sanpham_intrangchu_adapter.notifyDataSetChanged();

        sanphammoiIntrangchuAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {
                showDialogChiTietSanPham(sanphammoiIntrangchuAdapter.getViTriSp(position));
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
                themVaoGio(sanpham);
            }
        });
        sanpham_intrangchu_adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {
                showDialogChiTietSanPham(sanpham_intrangchu_adapter.getViTriSp(position));
            }
        });
        return view;
    }

    private int getSoLuongSp(int maSanPham) {
        for (Sanpham sanPham : sanphams) {
            if (sanPham.getMasanpham() == maSanPham) {
                return sanPham.getSoluong();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy sản phẩm
    }

    private void themVaoGio(Sanpham sanPham) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("KHACHHANG", Context.MODE_PRIVATE);
        int makh = sharedPreferences.getInt("makhachhang", 0);
        int maSanPham = sanPham.getMasanpham();
        int slSanPham = getSoLuongSp(maSanPham);
        gioHangArrayList = giohangDao.getDanhSachGioHangByMaNguoiDung(makh);
        boolean isProductInCart = false;
        for (Giohang gioHang : gioHangArrayList) {
            if (gioHang.getMaSanPham() == maSanPham) {
                isProductInCart = true;
                if (gioHang.getSoLuongMua() < slSanPham) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    giohangDao.updateGioHang(gioHang);
                    Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        if (!isProductInCart) {
            if (slSanPham > 0) {
                giohangDao.insertGioHang(new Giohang(maSanPham, makh, 1));
            } else {
                Toast.makeText(getActivity(), "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void updateText() {
        if (!hasMatchingProducts) {
            binding.tenkoquantrong.setText("Sản phẩm không có trong giỏ hàng.");
            binding.tenkoquantrong.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.nen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black));

        } else {
            binding.tenkoquantrong.setText("Sản phẩm ");

        }
    }

        private void showDialogChiTietSanPham(Sanpham sanPham) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogChitietsanphamBinding chiTietSanPhamBinding = DialogChitietsanphamBinding.inflate(getLayoutInflater());
        dialog.setContentView(chiTietSanPhamBinding.getRoot());

        if (sanPham != null) {
            Picasso.get().load(sanPham.getAnhsanpham()).into(chiTietSanPhamBinding.imaSp);
            chiTietSanPhamBinding.txtTenSanPham.setText(sanPham.getTensanpham());
            chiTietSanPhamBinding.txtGiaSanPham.setText(String.valueOf(sanPham.getGia()));
            chiTietSanPhamBinding.motaSanpham.setText(sanPham.getMota());


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
}
