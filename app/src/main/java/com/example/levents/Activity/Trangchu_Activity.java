package com.example.levents.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.levents.Fragment.Giohang_Fragment;
import com.example.levents.Fragment.Lichsu_dathang_Fragment;
import com.example.levents.Fragment.Sanpham_Fragment;
import com.example.levents.Fragment.Trangchu_Fragment;
import com.example.levents.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Trangchu_Activity extends AppCompatActivity {
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
                    Loadfragment(new Trangchu_Fragment(),false);
                } else if (itemID == R.id.bottom_nav_sanpham){
                    Loadfragment(new Sanpham_Fragment(),false);
                } else if(itemID == R.id.bottom_nav_giohang ){
                    Loadfragment(new Giohang_Fragment(),false);
                } else if (itemID == R.id.bottom_nav_thongtin){
                    Loadfragment(new Lichsu_dathang_Fragment(),false);
                }
                return true;
            }
        });
        Loadfragment(new Trangchu_Fragment(),true);
        SharedPreferences sharedPreferences = getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        String loaiTaiKhoan = sharedPreferences.getString("loaitaikhoan", "");
        int makhachhang = sharedPreferences.getInt("makhachhang", 0);
        Menu menu = bottomNavigationView.getMenu();
        if (loaiTaiKhoan.equals("khachhang")){
            MenuItem menuItem = menu.findItem(R.id.bottom_nav_khachhang);
            menuItem.setVisible(false);
        }
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