package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadon;
import com.example.levents.Model.Nhanvien;

import java.util.ArrayList;

public class Nhanvien_DAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Nhanvien_DAO(Context context) {
        this.dBhelper = new DBhelper(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("NHANVIEN", context.MODE_PRIVATE);
        } else {
            // Xử lý khi context là null, có thể thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.e(TAG, "Context is null in NguoiDungDao constructor");
        }
    }
    public Nhanvien getNhanVienByMaTaiKhoan(int maTaiKhoan) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Nhanvien nguoiDung = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM NHANVIEN WHERE manhanvien = ?", new String[]{String.valueOf(maTaiKhoan)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                nguoiDung = new Nhanvien();
                nguoiDung.setManhanvien(cursor.getInt(0));
                nguoiDung.setTendangnhap(cursor.getString(1));
                nguoiDung.setMatkhau(cursor.getString(2));
                nguoiDung.setHoten(cursor.getString(3));
                nguoiDung.setEmail(cursor.getString(4));
                nguoiDung.setSodienthoai(cursor.getString(5));
                nguoiDung.setDiachi(cursor.getString(6));
                nguoiDung.setLoaitaikhoan(cursor.getString(7));
                nguoiDung.setAnhnhanvien(cursor.getString(8));
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }
        return nguoiDung;
    }

    public boolean insertNhanVien(String tenDangNhap, String matKhau, String hoTen, String email, String soDienThoai, String diaChi, String loaiTaiKhoan, String anhNhanVien) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tendangnhap", tenDangNhap);
            values.put("matkhau", matKhau);
            values.put("hoten", hoTen);
            values.put("email", email);
            values.put("sodienthoai", soDienThoai);
            values.put("diachi", diaChi);
            values.put("loaitaikhoan", loaiTaiKhoan);
            values.put("anhnhanvien", anhNhanVien);

            long newRowId = db.insert("NHANVIEN", null, values);
            success = newRowId != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn nhanvien vào cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    @SuppressLint("Range")
    public ArrayList<Nhanvien> getAllNhanVien() {
        ArrayList<Nhanvien> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM NHANVIEN", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Nhanvien nhanVien = new Nhanvien();
                    nhanVien.setManhanvien(cursor.getInt(cursor.getColumnIndex("manhanvien")));
                    nhanVien.setTendangnhap(cursor.getString(cursor.getColumnIndex("tendangnhap")));
                    nhanVien.setMatkhau(cursor.getString(cursor.getColumnIndex("matkhau")));
                    nhanVien.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                    nhanVien.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    nhanVien.setSodienthoai(cursor.getString(cursor.getColumnIndex("sodienthoai")));
                    nhanVien.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
                    nhanVien.setLoaitaikhoan(cursor.getString(cursor.getColumnIndex("loaitaikhoan")));
                    nhanVien.setAnhnhanvien(cursor.getString(cursor.getColumnIndex("anhnhanvien")));
                    list.add(nhanVien);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu nhân viên từ cơ sở dữ liệu", e);
        }
        return list;
    }
    public boolean updatenhanvien(Nhanvien nguoiDung) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhnhanvien", nguoiDung.getAnhnhanvien());
        values.put("hoten", nguoiDung.getHoten());
        values.put("tendangnhap", nguoiDung.getTendangnhap());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("matkhau", nguoiDung.getMatkhau());
        values.put("email", nguoiDung.getEmail());
        values.put("diachi", nguoiDung.getDiachi());
        long check = db.update("NHANVIEN", values, "manhanvien = ?", new String[]{String.valueOf(nguoiDung.getManhanvien())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateNhanVien(int maNhanVien, String tenDangNhap, String matKhau, String hoTen, String email, String soDienThoai, String diaChi, String loaiTaiKhoan, String anhNhanVien) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tendangnhap", tenDangNhap);
            values.put("matkhau", matKhau);
            values.put("hoten", hoTen);
            values.put("email", email);
            values.put("sodienthoai", soDienThoai);
            values.put("diachi", diaChi);
            values.put("loaitaikhoan", loaiTaiKhoan);
            values.put("anhnhanvien", anhNhanVien);

            int rowsAffected = db.update("NHANVIEN", values, "manhanvien = ?", new String[]{String.valueOf(maNhanVien)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật thông tin nhân viên trong cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean deleteNhanVien(int maNhanVien) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            int rowsAffected = db.delete("NHANVIEN", "manhanvien = ?", new String[]{String.valueOf(maNhanVien)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi xóa nhân viên khỏi cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean checkDangKy(Nhanvien nguoiDung) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendangnhap", nguoiDung.getTendangnhap());
        values.put("matkhau", nguoiDung.getMatkhau());
        values.put("hoten", nguoiDung.getHoten());
        values.put("email", nguoiDung.getEmail());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("diachi", nguoiDung.getDiachi());
        values.put("loaitaikhoan", nguoiDung.getLoaitaikhoan());
        values.put("anhnhanvien", nguoiDung.getAnhnhanvien());
        long result = db.insert("NHANVIEN", null, values);
        return result != -1;
    }
    public ArrayList<Hoadon> getDsDonHangTheoNhanVien(int manhanvien) {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            String query = "SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai, NHANVIEN.hoten " +
                    "FROM HOADON " +
                    "INNER JOIN KHACHHANG ON HOADON.makhachhang = KHACHHANG.makhachhang " +
                    "LEFT JOIN NHANVIEN ON HOADON.manhanvien = NHANVIEN.manhanvien " +
                    "WHERE HOADON.manhanvien = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(manhanvien)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Hoadon donHang = new Hoadon();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setMaTaiKhoan(cursor.getInt(1));
                    donHang.setTenTaiKhoan(cursor.getString(2));
                    donHang.setNgayDatHang(cursor.getString(3));
                    donHang.setTongTien(cursor.getInt(4));
                    donHang.setTrangthai(cursor.getString(5));
                    String tenNhanVien = cursor.getString(6); // Lấy tên nhân viên từ cột thứ 7
                    donHang.setTennhanvien(tenNhanVien != null ? tenNhanVien : "Không có thông tin"); // Đảm bảo không null
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }


}
