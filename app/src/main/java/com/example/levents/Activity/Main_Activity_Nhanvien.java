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

import com.example.levents.Fragment.DoiMatKhauFragment;
import com.example.levents.Fragment.HoaDonAdminFragment;
import com.example.levents.Fragment.KhachHangAdminFragment;
import com.example.levents.Fragment.QLSpham_Fragment;
import com.example.levents.Fragment.SanPhamAdminFragment;
import com.example.levents.Fragment.Thongke_Fragment;
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
//        QL_nguoidung_Fragment fragment = new QL_nguoidung_Fragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.layout_content, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        // set toolbar
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
                    fragment = new KhachHangAdminFragment();
                } else if (menuItem.getItemId() == R.id.hoaDon) {
                    toolbar.setTitle("Hóa đơn");
                    fragment = new HoaDonAdminFragment();
                } else if (menuItem.getItemId() == R.id.thongKe) {
                    toolbar.setTitle("Thống kê");
                    fragment = new Thongke_Fragment();
                } else if (menuItem.getItemId() == R.id.doiMatKhau) {
                    toolbar.setTitle("Đổi mật khẩu");
                    fragment = new DoiMatKhauFragment();
                } else if (menuItem.getItemId() == R.id.dangXuat) {
                    finish();
                    startActivity(new Intent(Main_Activity_Nhanvien.this, Login_Activity.class));
                }
                
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                drawerLayout.close();
                return true;
            }
        });

    }

}