package com.example.levents.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.NguoiDung_Adapter;
import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Fragment.QL_nguoidung_Fragment;
import com.example.levents.Model.Khachhang;
import com.example.levents.R;

import java.util.ArrayList;

public class Main_Activity_Nhanvien extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nhanvien);
        QL_nguoidung_Fragment fragment = new QL_nguoidung_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }

}