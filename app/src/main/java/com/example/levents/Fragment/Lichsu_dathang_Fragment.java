package com.example.levents.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.levents.Adapter.Lichsu_dathang_Adapter;
import com.example.levents.DAO.Donhang_DAO;
import com.example.levents.Model.Hoadon;
import com.example.levents.databinding.FragmentLichsuDathangBinding;

import java.util.ArrayList;


public class Lichsu_dathang_Fragment extends Fragment {
    FragmentLichsuDathangBinding binding;
    private ArrayList<Hoadon> list;
    private Donhang_DAO donhangDao;
    private Lichsu_dathang_Adapter lichsuDathangAdapter;
    View view;
    public static final int DEFAULT_VALUE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLichsuDathangBinding binding = FragmentLichsuDathangBinding.inflate(inflater, container, false);
        SharedPreferences preferencesKH = getActivity().getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        int mand = preferencesKH.getInt("makhachhang", 0);

        donhangDao = new Donhang_DAO(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rcvLichSuDonHang.setLayoutManager(layoutManager);
        list = donhangDao.getDonHangByMaTaiKhoan(mand);
        lichsuDathangAdapter = new Lichsu_dathang_Adapter(getActivity(),list);
        binding.rcvLichSuDonHang.setAdapter(lichsuDathangAdapter);

        return binding.getRoot();
    }

}