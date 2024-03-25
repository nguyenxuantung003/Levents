package com.example.levents.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.levents.Activity.Main_Activity_Nhanvien;
import com.example.levents.Adapter.NguoiDung_Adapter;
import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Model.Khachhang;
import com.example.levents.R;

import java.util.ArrayList;


public class QL_nguoidung_Fragment extends Fragment {
    RecyclerView recyclerViewND;
    NguoiDung_Adapter nguoiDungAdapter;
    ArrayList<Khachhang> khachhangArrayList;
    Khachang_DAO khachangDao;
 ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_nguoidung_,container,false);


        recyclerViewND = view.findViewById(R.id.rcv_nguoidung);
        recyclerViewND.setLayoutManager(new LinearLayoutManager(getContext()));


        khachangDao = new Khachang_DAO(getContext().getApplicationContext());
        loadNguoiDungData();
        return view;
    }
    private void loadNguoiDungData() {
        khachangDao = new Khachang_DAO(getContext().getApplicationContext());
        khachhangArrayList = khachangDao.getAllNguoiDung();

        nguoiDungAdapter = new NguoiDung_Adapter(getContext(),khachangDao, khachhangArrayList);
        recyclerViewND.setAdapter(nguoiDungAdapter);
    }
}