package com.example.levents.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.levents.Adapter.Hoadonchitiet_Adapter;
import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.FragmentHoadonchitietBinding;

import java.util.ArrayList;

public class Hoadonchitiet_Fragment extends Fragment {
        FragmentHoadonchitietBinding binding;
        private ArrayList<Hoadonchitiet> list = new ArrayList<>();
        private Hoadonchitiet_Adapter hoadonchitietAdapter;
        private Chitiethoadon_DAO chitiethoadonDao;

    public Hoadonchitiet_Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHoadonchitietBinding.inflate(inflater, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvDonHangChiTiet.setLayoutManager(layoutManager);
        chitiethoadonDao = new Chitiethoadon_DAO(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("mahoadon", 0);
            Log.d("Mã đơn hàng", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chitiethoadonDao.getChiTietDonHangByMaDonHang(maDonHang);
                hoadonchitietAdapter = new Hoadonchitiet_Adapter(list, getContext());
                binding.rcvDonHangChiTiet.setAdapter(hoadonchitietAdapter);
            }
        }
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        return binding.getRoot();
    }
}