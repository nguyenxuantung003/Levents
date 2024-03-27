package com.example.levents.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Model.Khachhang;
import com.example.levents.databinding.ActivityRegisterBinding;

public class Register_Activity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    Khachhang khachhang = new Khachhang();


    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateDangKy()) {
                    clickDangKy();
                }
            }
        });


    }
    private void clickDangKy() {
        // Lấy thông tin từ các trường nhập liệu
        khachhang.setTendangnhap(binding.edtTenDangNhap.getText().toString().trim());
        khachhang.setMatkhau(binding.edtMatKhauRe.getText().toString().trim());
        khachhang.setHoten(binding.edtHoTen.getText().toString());
        khachhang.setSodienthoai(binding.edtSoDienThoai.getText().toString().trim());
        khachhang.setDiachi(binding.edtdiachi.getText().toString());
        khachhang.setEmail(binding.edtEmail.getText().toString().trim());
       // Đặt số tiền mặc định khi đăng ký
        khachhang.setAnhkhachhang(binding.edtanhkhachhang.getText().toString());
        khachhang.setLoaitaikhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký

        // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
        Khachang_DAO dao = new Khachang_DAO(Register_Activity.this);
        boolean result = dao.checkDangKy(khachhang);

        if (result) {
            // Đăng ký thành công
            Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
            startActivity(intent);
            Log.d("HoadonData", "dang ki thanh cong");
            Log.d("HoadonData", "Ten dang nhap: " + khachhang.getTendangnhap());
            Log.d("HoadonData", "Mat khau: " + khachhang.getMatkhau());
        } else {
            // Đăng ký thất bại
            Toast.makeText(Register_Activity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validateDangKy() {
        Khachang_DAO dao = new Khachang_DAO(Register_Activity.this);
        String tenDangNhap = binding.edtTenDangNhap.getText().toString().trim();
        String matKhau = binding.edtMatKhauRe.getText().toString().trim();
        String nhapLaiMatKhau = binding.edtNhapLaiMatKhau.getText().toString().trim();
        String hoTen = binding.edtHoTen.getText().toString().trim();
        String sdt = binding.edtSoDienThoai.getText().toString().trim();
        String diaChi = binding.edtdiachi.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String anh = binding.edtanhkhachhang.getText().toString().trim();
        boolean isValid = true;
        if (tenDangNhap.isEmpty()) {
            binding.tipLTendangnhap.setError("Vui lòng nhập tên đăng nhập");
            isValid = false;
        } else if (dao.tenDangNhapDaTonTai(tenDangNhap)) {
            binding.tipLTendangnhap.setError("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác");
            return false;
        } else {
            binding.tipLTendangnhap.setError(null);
        }

        if (matKhau.isEmpty()) {
            binding.tipLMatkhau.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else {
            binding.tipLMatkhau.setError(null);
        }
        if (anh.isEmpty()) {
            binding.tipLAnhkhachhang.setError("Vui lòng nhập link ảnh");
            isValid = false;
        } else {
            binding.tipLAnhkhachhang.setError(null);
        }

        if (nhapLaiMatKhau.isEmpty()) {
            binding.tipLNhaplaimatkhau.setError("Vui lòng nhập lại mật khẩu");
            isValid = false;
        } else if (!nhapLaiMatKhau.equals(matKhau)) {
            binding.tipLNhaplaimatkhau.setError("Mật khẩu không trùng nhau");
            isValid = false;
        } else {
            binding.tipLNhaplaimatkhau.setError(null);
        }

        if (hoTen.isEmpty()) {
            binding.tipLHoten.setError("Vui lòng nhập họ tên");
            isValid = false;
        } else {
            binding.tipLHoten.setError(null);
        }

        if (sdt.isEmpty()) {
            binding.tipLSodienthoai.setError("Vui lòng nhập số điện thoại");
            isValid = false;
        } else if (!isValidPhoneNumber(sdt)) {
            binding.tipLSodienthoai.setError("Số điện thoại không hợp lệ");
            isValid = false;
        } else {
            binding.tipLSodienthoai.setError(null);
        }

        if (diaChi.isEmpty()) {
            binding.tipLDiachi.setError("Vui lòng nhập địa chỉ");
            isValid = false;
        } else {
            binding.tipLDiachi.setError(null);
        }

        if (email.isEmpty()) {
            binding.tipLEmail.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!isValidEmail(email)) {
            binding.tipLEmail.setError("Email không hợp lệ");
            isValid = false;
        } else {
            binding.tipLEmail.setError(null);
        }

        return isValid;

    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "0\\d{9}";
        return phoneNumber.matches(regex);
    }

    // Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?";
        return email.matches(regex);
    }
}