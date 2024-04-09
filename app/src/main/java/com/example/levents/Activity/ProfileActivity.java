package com.example.levents.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.levents.R;
import com.example.levents.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
        ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences preferences = getSharedPreferences("KHACHHANG", MODE_PRIVATE);
        int mand = preferences.getInt("makhachhang",0);
        String tenDN = preferences.getString("tendangnhap", "");
        String matkhau = preferences.getString("matkhau", "");
        String hoten = preferences.getString("hoten", "");
        String email = preferences.getString("email", "");
        String sodienthoai = preferences.getString("sodienthoai", "");
        String diachi = preferences.getString("diachi", "");
        String loaitaikhoan = preferences.getString("loaitaikhoan", "");
        String urlAnh = preferences.getString("anhkhachhang","data:image/jpeg;base64,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");
        Log.d("TAG", "anh khach hang" + urlAnh);
        binding.hiName.setText("Hi, " + hoten);
        binding.txtPMaNguoiDung.setText("Mã tài khoản: " + String.valueOf(mand));
        binding.txtPTenDangNhap.setText("Tên đăng nhập: " + tenDN);
        binding.txtPHoTen.setText("Họ tên: " + hoten);
        binding.txtPEmail.setText("Email: " + email);
        binding.txtPSoDienThoai.setText("Số điện thoại: " + sodienthoai);
        binding.txtPDiaChi.setText("Địa chỉ: " + diachi);
        binding.txtPLoaiTaiKhoan.setText("Loại tài khoản: " + loaitaikhoan);
        Picasso.get().load(urlAnh).into(binding.imgAvatarProfile);
        binding.btnPDangXuat.setOnClickListener(view -> {
            finish();
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
//                                                   Profile.this.getOnBackPressedDispatcher().onBackPressed();
                                                   finish();
                                                   ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, Trangchu_Activity.class));
                                               }
                                           }

        );
        binding.btnhoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Updatethongtin_Activity.class));
            }
        });
    }
}