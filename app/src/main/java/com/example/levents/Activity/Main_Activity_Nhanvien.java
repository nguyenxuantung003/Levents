package com.example.levents.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.levents.Fragment.DS_hoadon_Fragment;
import com.example.levents.Fragment.QLSpham_Fragment;
import com.example.levents.Fragment.QL_nguoidung_Fragment;
import com.example.levents.Fragment.TK_hoadon_nv_Fragment;
import com.example.levents.R;
import com.google.android.material.navigation.NavigationView;

public class Main_Activity_Nhanvien extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nhanvien);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                Main_Activity_Nhanvien.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        );
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                if (menuItem.getItemId() == R.id.sanPham) {
                    toolbar.setTitle("Sản phẩm");
                    fragment = new QLSpham_Fragment();
                } else if (menuItem.getItemId() == R.id.khachHang) {
                    toolbar.setTitle("Khách hàng");
                    fragment = new QL_nguoidung_Fragment();
                } else if (menuItem.getItemId() == R.id.hoaDon) {
                    toolbar.setTitle("Hóa đơn mới");
                    fragment = new DS_hoadon_Fragment();
                } else if (menuItem.getItemId() == R.id.thongKe) {
                    toolbar.setTitle("Lịch sử hoá đơn của bạn");
                    fragment = new TK_hoadon_nv_Fragment();
                }  else if (menuItem.getItemId() == R.id.dangXuat) {
                    finish();
                    startActivity(new Intent(Main_Activity_Nhanvien.this, Login_Activity.class));
                }
                
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                drawerLayout.close();
                return true;
            }
        });
        Loadfragment(new QLSpham_Fragment(),true);

    }
    private void Loadfragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout,fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }

}