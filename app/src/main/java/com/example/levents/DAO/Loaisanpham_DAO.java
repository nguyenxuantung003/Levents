package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;

import java.util.ArrayList;

public class Loaisanpham_DAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public boolean insertLoaiSanPham(String tenLoaiSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tenloaisanpham", tenLoaiSanPham);

            long newRowId = db.insert("LOAISANPHAM", null, values);
            success = newRowId != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn loại sản phẩm vào cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public ArrayList<String> getAllLoaiSanPham() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM LOAISANPHAM", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String tenLoaiSanPham = cursor.getString(cursor.getColumnIndex("tenloaisanpham"));
                    list.add(tenLoaiSanPham);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu loại sản phẩm từ cơ sở dữ liệu", e);
        }
        return list;
    }
    public boolean updateLoaiSanPham(int maLoaiSanPham, String tenLoaiSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tenloaisanpham", tenLoaiSanPham);

            int rowsAffected = db.update("LOAISANPHAM", values, "maloaisanpham = ?", new String[]{String.valueOf(maLoaiSanPham)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật loại sản phẩm trong cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean deleteLoaiSanPham(int maLoaiSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            int rowsAffected = db.delete("LOAISANPHAM", "maloaisanpham = ?", new String[]{String.valueOf(maLoaiSanPham)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi delete", e);
        } finally {
            db.close();
        }
        return success;
    }
}
