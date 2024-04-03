package com.example.levents.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.levents.Adapter.Fragment_Trangchu_Adapter;
import com.example.levents.Adapter.Fragment_sanpham_Adapter;
import com.example.levents.Adapter.Giohang_Adapter;
import com.example.levents.DAO.Giohang_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.OnAddToCart;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Giohang;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import com.example.levents.databinding.DialogChitietsanphamBinding;
import com.example.levents.databinding.FragmentSanphamBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Sanpham_Fragment extends Fragment {
    FragmentSanphamBinding binding;
    View view;
    ArrayList<Sanpham> sanphams;
    Sanpham_DAO sanphamDao;
    Fragment_Trangchu_Adapter fragmentTrangchuAdapter;
    Fragment_sanpham_Adapter fragmentSanphamAdapter;
    private boolean hasMatchingProducts = true;
    private ArrayList<Giohang> gioHangArrayList = new ArrayList<>();
    private Giohang_DAO giohangDao;
    private Giohang_Adapter giohangAdapter;


    public Sanpham_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSanphamBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        SharedPreferences preferences = getActivity().getSharedPreferences("KHACHHANG", Context.MODE_PRIVATE);
        String hoten = preferences.getString("hoten", "");
        giohangDao = new Giohang_DAO(getActivity());
        gioHangArrayList = giohangDao.getDSGioHang();
        giohangAdapter = new Giohang_Adapter(getActivity(), gioHangArrayList);
        sanphamDao = new Sanpham_DAO(getContext());
        sanphams = sanphamDao.getsanphamall();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        binding.rcvSanpham.setLayoutManager(gridLayoutManager);
        fragmentSanphamAdapter = new Fragment_sanpham_Adapter(sanphams, getContext());
        binding.rcvSanpham.setAdapter(fragmentSanphamAdapter);

        fragmentSanphamAdapter.notifyDataSetChanged();
        fragmentSanphamAdapter.setOnAddToCartClickListenerSanpham(new OnAddToCart() {
            @Override
            public void onAddToCartClick(Sanpham sanpham) {
                themVaoGio(sanpham);
            }
        });
        fragmentSanphamAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {
                showDialogChiTietSanPham(fragmentSanphamAdapter.getViTriSp(position));
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
    private void showDialogChiTietSanPham(Sanpham sanPham) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogChitietsanphamBinding chiTietSanPhamBinding = DialogChitietsanphamBinding.inflate(getLayoutInflater());
        dialog.setContentView(chiTietSanPhamBinding.getRoot());

        if (sanPham != null) {
            Picasso.get().load(sanPham.getAnhsanpham()).into(chiTietSanPhamBinding.imaSp);
            chiTietSanPhamBinding.txtTenSanPham.setText(sanPham.getTensanpham());
            chiTietSanPhamBinding.txtGiaSanPham.setText( String.valueOf(sanPham.getGia()));
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