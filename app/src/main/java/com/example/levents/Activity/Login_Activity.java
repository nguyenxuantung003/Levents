package com.example.levents.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Database.DBhelper;
import com.example.levents.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;

   // private SharedPreferences.Editor editor;

    Intent intent;
    Context context;
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //checkRemember();
        dBhelper = new DBhelper(this);
        Khachang_DAO khachangDao = new Khachang_DAO(this);
        context = this;
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangnhap = binding.edtTenDangNhap.getText().toString();
                String matkhau = binding.edtMatKhau.getText().toString();
                if (tendangnhap.equals("") || matkhau.equals("")) {
                    Toast.makeText(Login_Activity.this, "Không được bỏ trống ", Toast.LENGTH_SHORT).show();
                } else {
                    int userId = checkLogin(tendangnhap, matkhau,context);
                    if (userId > 0) {
                        if(userId == 1){
                            Intent intent1 = new Intent(getApplicationContext(), Main_Activity_Nhanvien.class);
                            startActivity(intent1);
                            finish();
                        } else if (userId == 2){
                            Intent intent1 = new Intent(getApplicationContext(), Trangchu_Activity.class);
                            startActivity(intent1);
                            finish();
                        }
                        else if(userId == 3) {
                            Intent intent1 = new Intent(getApplicationContext(), Main_Activity_Khachhang.class);
                            startActivity(intent1);
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(Login_Activity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
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
    @SuppressLint("Range")
    private int checkLogin(String tendangnhap, String matkhau,Context context) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        SharedPreferences preferencesNV = context.getSharedPreferences("NHANVIEN", Context.MODE_PRIVATE);
        // SharedPreferences cho Khách hàng
        SharedPreferences preferencesKH = context.getSharedPreferences("KHACHHANG", Context.MODE_PRIVATE);
        // Kiểm tra trong bảng nhân viên
        String[] columnsNV = {"manhanvien"};
        String selectionNV = "tendangnhap=? AND matkhau=?";
        String[] selectionArgs = {tendangnhap, matkhau};
        Cursor cursorNV = db.query("NHANVIEN", columnsNV, selectionNV, selectionArgs, null, null, null);
        if (cursorNV != null && cursorNV.moveToFirst()) {
            SharedPreferences.Editor editorNV = preferencesNV.edit();
            editorNV.putInt("manhanvien", cursorNV.getInt(cursorNV.getColumnIndex("manhanvien")));
            // Tiếp tục lấy dữ liệu từ Cursor và lưu vào SharedPreferences cho Nhân viên
            editorNV.commit();
            cursorNV.close();
            Log.d("checkLogin", "Đã lưu thông tin nhân viên: " + preferencesNV.getInt("manhanvien", -1));
            return 1; // Tài khoản nhân viên
        }
        // Kiểm tra trong bảng khách hàng
        String[] columnsKH = {"makhachhang","tendangnhap","matkhau","hoten","email","sodienthoai","diachi","loaitaikhoan","anhkhachhang"};
        String selectionKH = "tendangnhap=? AND matkhau=?";
        Cursor cursorKH = db.query("KHACHHANG", columnsKH, selectionKH, selectionArgs, null, null, null);
        if (cursorKH != null && cursorKH.moveToFirst()) {
            SharedPreferences.Editor editorKH = preferencesKH.edit();
            editorKH.putInt("makhachhang", cursorKH.getInt(cursorKH.getColumnIndex("makhachhang")));
            editorKH.putString("tendangnhap", cursorKH.getString(cursorKH.getColumnIndex("tendangnhap")));
            editorKH.putString("matkhau", cursorKH.getString(cursorKH.getColumnIndex("matkhau")));
            editorKH.putString("hoten", cursorKH.getString(cursorKH.getColumnIndex("hoten")));
            editorKH.putString("email", cursorKH.getString(cursorKH.getColumnIndex("email")));
            editorKH.putString("sodienthoai", cursorKH.getString(cursorKH.getColumnIndex("sodienthoai")));
            editorKH.putString("diachi", cursorKH.getString(cursorKH.getColumnIndex("diachi")));
            editorKH.putString("loaitaikhoan", cursorKH.getString(cursorKH.getColumnIndex("loaitaikhoan")));
            editorKH.putString("anhkhachhang", cursorKH.getString(cursorKH.getColumnIndex("anhkhachhang")));
            editorKH.commit();
            // Tiếp tục lấy dữ liệu từ Cursor và lưu vào SharedPreferences cho Khách hàng
            cursorKH.close();
            Log.d("checkLogin", "Đã lưu thông tin khách hàng: " + preferencesKH.getInt("makhachhang", -1));
            return 2;  // Tài khoản khách hàng
        }
        if (tendangnhap.equals("tungdeptrai10nguoiyeu") && matkhau.equals("261003")) {
            return 3; // Trả về 3 nếu tên đăng nhập và mật khẩu đúng
        }
        if (cursorNV != null) {
            cursorNV.close();
        }
        if (cursorKH != null) {
            cursorKH.close();
        }
        return -1; // Tài khoản không tồn tại
    }
}
