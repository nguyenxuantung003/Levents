package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Khachhang;

import java.util.ArrayList;

public class Khachang_DAO {
    static DBhelper dBhelper;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    public ArrayList<Khachhang> getAllKhachhang() {
        ArrayList<Khachhang> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Khachhang khachhang = new Khachhang();
                    khachhang.setMakhachhang(cursor.getInt(0));
                    khachhang.setTendangnhap(cursor.getString(1));
                    khachhang.setMatkhau(cursor.getString(2));
                    khachhang.setHoten(cursor.getString(3));
                    khachhang.setEmail(cursor.getString(4));
                    khachhang.setSodienthoai(cursor.getString(5));
                    khachhang.setDiachi(cursor.getString(6));
                    khachhang.setLoaitaikhoan(cursor.getString(7));
                    khachhang.setAnhkhachhang(cursor.getString(8));
                    list.add(khachhang);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }

    public static boolean checkDangNhap(String tenDangNhap, String matKhau) {
        String role;
        Log.d(TAG, "CheckDangNhap: " + tenDangNhap + " - " + matKhau);
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
           Cursor cursorKH = database.rawQuery("SELECT * FROM KHACHHANG WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});
            if (cursorKH.getCount() > 0) {
                cursorKH.moveToFirst();
                role = "khach hang";
                editor = sharedPreferences.edit();
                editor.putInt("makhachhang", cursorKH.getInt(0));
                editor.putString("tendangnhap", cursorKH.getString(1));
                editor.putString("matkhau", cursorKH.getString(2));
                editor.putString("hoten", cursorKH.getString(3));
                editor.putString("email", cursorKH.getString(4));
                editor.putString("sodienthoai", cursorKH.getString(5));
                editor.putString("diachi", cursorKH.getString(6));
                editor.putString("loaitaikhoan", cursorKH.getString(7));
                editor.putString("anhtaikhoan", cursorKH.getString(8));
                editor.apply();
                return true;
            } else {
               Cursor cursorNV = database.rawQuery("SELECT * FROM NHANVIEN WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});
                if (cursorNV.getCount() > 0) {
                    cursorNV.moveToFirst();
                    role = "nhanvien";
                    editor = sharedPreferences.edit();
                    editor.putInt("manhanvien", cursorNV.getInt(0));
                    editor.putString("tendangnhap", cursorNV.getString(1));
                    editor.putString("matkhau", cursorNV.getString(2));
                    editor.putString("hoten", cursorNV.getString(3));
                    editor.putString("email", cursorNV.getString(4));
                    editor.putString("sodienthoai", cursorNV.getString(5));
                    editor.putString("diachi", cursorNV.getString(6));
                    editor.putString("loaitaikhoan", cursorNV.getString(7));
                    editor.putString("anhtaikhoan", cursorNV.getString(8));
                    editor.apply();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }


}
