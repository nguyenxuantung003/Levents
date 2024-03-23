package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.Context;
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

    public Khachang_DAO(Context context) {
        this.dBhelper= new DBhelper(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("NGUOIDUNG", context.MODE_PRIVATE);
        } else {
            // Xử lý khi context là null, có thể thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.e(TAG, "Context is null in NguoiDungDao constructor");
        }
    }
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
}
