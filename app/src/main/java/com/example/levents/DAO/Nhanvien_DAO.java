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
        values.put("anhkhachhang", nguoiDung.getAnhnhanvien());
        long result = db.insert("NHANVIEN", null, values);
        return result != -1;
    }
}
