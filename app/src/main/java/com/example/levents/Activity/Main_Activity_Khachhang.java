package com.example.levents.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.levents.Fragment.QLSpham_Fragment;
import com.example.levents.Fragment.QL_nguoidung_Fragment;
import com.example.levents.Fragment.QL_nhanvien_Fragment;
import com.example.levents.Fragment.QLhoadon_Fragment;
import com.example.levents.Fragment.Thongke_Fragment;
import com.example.levents.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_Activity_Khachhang extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        anhxa();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if(itemID == R.id.bottom_nav_trangchu){
                    Loadfragment(new QL_nhanvien_Fragment(),false);
                } else if (itemID == R.id.bottom_nav_sanpham){
                    Loadfragment(new QLSpham_Fragment(),false);
                } else if(itemID == R.id.bottom_nav_giohang ){
                    Loadfragment(new QLhoadon_Fragment(),false);
                } else if (itemID == R.id.bottom_nav_thongtin){
                    Loadfragment(new Thongke_Fragment(),false);
                } else if(itemID == R.id.bottom_nav_khachhang){
                    Loadfragment(new QL_nguoidung_Fragment(),false);
                }
                return true;
            }
        });
        Loadfragment(new QL_nhanvien_Fragment(),true);
        SharedPreferences sharedPreferences = getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        String loaiTaiKhoan = sharedPreferences.getString("loaitaikhoan", "");
        int makhachhang = sharedPreferences.getInt("makhachhang", 0);
    }

    private void Loadfragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.framelayout_trangchu,fragment);
        } else {
            fragmentTransaction.replace(R.id.framelayout_trangchu, fragment);
        }

        fragmentTransaction.commit();
    }

    private void anhxa(){
        bottomNavigationView = findViewById(R.id.bottomnavicationview_trangchu);
        frameLayout = findViewById(R.id.framelayout_trangchu);

    }
}