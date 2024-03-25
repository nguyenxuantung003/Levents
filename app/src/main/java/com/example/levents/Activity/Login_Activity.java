package com.example.levents.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Database.DBhelper;
import com.example.levents.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    Intent intent;
    Context context;
    DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dBhelper = new DBhelper(this);
      Khachang_DAO khachangDao = new Khachang_DAO(this);
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangnhap = binding.edtTenDangNhap.getText().toString();
                String matkhau = binding.edtMatKhau.getText().toString();
                if(tendangnhap.equals("") || matkhau.equals("")){
                    Toast.makeText(Login_Activity.this,"Khong duoc bo trong ", Toast.LENGTH_SHORT).show();
                } else {
                    int userRole = checkLogin(tendangnhap, matkhau);
                    if(userRole == 2){
                        Intent intent1 = new Intent(getApplicationContext(),Trangchu_Activity.class);
                        startActivity(intent1);
                    } else if(userRole ==1) {
                        Intent intent1 = new Intent(getApplicationContext(),Main_Activity_Nhanvien.class);
                        startActivity(intent1);
                    }
                }
            }
        });
        binding.tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Login_Activity.this,Register_Activity.class);
                startActivity(intent);
            }
        });
    }
    private int checkLogin(String tendangnhap, String matkhau) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        // Kiểm tra trong bảng nhân viên
        String[] columnsKH = {"makhachhang"};
        String[] columnsNV = {"manhanvien"};
        String selection = "tendangnhap=? AND matkhau=?";
        String[] selectionArgs = {tendangnhap, matkhau};
        Cursor cursor = db.query("NHANVIEN", columnsNV, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return 1; // Tài khoản nhân viên
        }
        cursor.close();
        // Kiểm tra trong bảng khách hàng
        cursor = db.query("KHACHHANG", columnsKH, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return 2; // Tài khoản khách hàng
        }
        cursor.close();
        return 0; // Tài khoản không tồn tại
    }
}
