package com.example.levents.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.R;
import com.example.levents.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Khachang_DAO khachangDao;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String tendangnhap = binding.edtTenDangNhap.getText().toString();
        String matkhau = binding.edtMatKhau.getText().toString();
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tendangnhap.isEmpty() || matkhau.isEmpty()) {
                    Toast.makeText(Login_Activity.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (khachangDao.checkDangNhap(tendangnhap, matkhau)) {
                    String role = preferences.getString("loaitaikhoan", ""); // Lấy vai trò của người dùng
                    if (role.equals("khách hàng")) {
                        intent = new Intent(Login_Activity.this, Main_Activity_Khachhang.class);
                    } else if (role.equals("nhân viên")) {
                        intent = new Intent(Login_Activity.this, Main_Activity_Nhanvien.class);
                    }
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login_Activity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });
    }
}