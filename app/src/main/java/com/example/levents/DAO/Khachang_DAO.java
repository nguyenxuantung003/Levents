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
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


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

    public boolean checkDangNhap(String tenDangNhap, String matKhau) {
        Log.d(TAG, "CheckDangNhap: " + tenDangNhap + " - " + matKhau);
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                editor = sharedPreferences.edit();
                editor.putInt("makhachhang", cursor.getInt(0));
                editor.putString("tendangnhap", cursor.getString(1));
                editor.putString("matkhau", cursor.getString(2));
                editor.putString("hoten", cursor.getString(3));
                editor.putString("email", cursor.getString(4));
                editor.putString("sodienthoai", cursor.getString(5));
                editor.putString("diachi", cursor.getString(6));
                editor.putString("loaitaikhoan", cursor.getString(7));
                editor.putString("anhtaikhoan", cursor.getString(8));
                editor.apply();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

}
