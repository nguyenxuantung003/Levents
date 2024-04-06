package com.example.levents.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.levents.Adapter.ls_dathang_chitiet_Adapter;
import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.R;
import com.example.levents.databinding.FragmentLSDathangChitietBinding;

import java.util.ArrayList;

public class LS_dathang_chitiet_Fragment extends Fragment {
    FragmentLSDathangChitietBinding binding;
    private ArrayList<Hoadonchitiet> list = new ArrayList<>();
    Chitiethoadon_DAO chiTietDao;
    private ls_dathang_chitiet_Adapter adapterDonHangChiTiet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLSDathangChitietBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvLichSuDonHang.setLayoutManager(layoutManager);
        chiTietDao = new Chitiethoadon_DAO(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("makhachhang", 0);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("mahoadon", 0);
            Log.d("Mã đơn hàng", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterDonHangChiTiet = new ls_dathang_chitiet_Adapter(list, getContext());
                binding.rcvLichSuDonHang.setAdapter(adapterDonHangChiTiet);
            }
        }

        binding.imgBackLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lichsu_dathang_Fragment frgLichSuDonHang = new Lichsu_dathang_Fragment();//fragment được chuyển đến sau khi ấn
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.framelayout_trangchu, frgLichSuDonHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}