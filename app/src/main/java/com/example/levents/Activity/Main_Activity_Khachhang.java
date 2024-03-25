package com.example.levents.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.NguoiDung_Adapter;
import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Model.Khachhang;
import com.example.levents.R;


import java.util.ArrayList;

public class Main_Activity_Khachhang extends AppCompatActivity {
    RecyclerView recyclerViewND;
    NguoiDung_Adapter nguoiDungAdapter;
    ArrayList<Khachhang> khachhangArrayList;
    Khachang_DAO khachangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khachhang);
        recyclerViewND = findViewById(R.id.rcv_nguoidung);
        reloaddata();
    }

    public void reloaddata() {
        khachangDao = new Khachang_DAO(getApplication());
        khachhangArrayList = khachangDao.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDung_Adapter(Main_Activity_Khachhang.this, khachangDao, khachhangArrayList);
        recyclerViewND.setAdapter(nguoiDungAdapter);
        recyclerViewND.setLayoutManager(new LinearLayoutManager(this));
    }
}