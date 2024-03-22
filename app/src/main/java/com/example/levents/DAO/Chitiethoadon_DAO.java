package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadonchitiet;

import java.util.ArrayList;

public class Chitiethoadon_DAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public boolean insertChiTietHoaDon(int maHoaDon, int maSanPham, int soLuong, int donGia, int thanhTien) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("mahoadon", maHoaDon);
            values.put("masanpham", maSanPham);
            values.put("soluong", soLuong);
            values.put("dongia", donGia);
            values.put("thanhtien", thanhTien);

            long newRowId = db.insert("CHITIETHOADON", null, values);
            success = newRowId != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn hoadon vào cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    @SuppressLint("Range")
    public ArrayList<Hoadonchitiet> getAllChiTietHoaDon() {
        ArrayList<Hoadonchitiet> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM CHITIETHOADON", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Hoadonchitiet hoadonchitiet = new Hoadonchitiet();
                    hoadonchitiet.setMachitiethoadon(cursor.getInt(cursor.getColumnIndex("machitiethoadon")));
                    hoadonchitiet.setMahoadon(cursor.getInt(cursor.getColumnIndex("mahoadon")));
                    hoadonchitiet.setMasanpham(cursor.getInt(cursor.getColumnIndex("masanpham")));
                    hoadonchitiet.setSoluong(cursor.getInt(cursor.getColumnIndex("soluong")));
                    hoadonchitiet.setDongia(cursor.getInt(cursor.getColumnIndex("dongia")));
                    hoadonchitiet.setThanhtien(cursor.getInt(cursor.getColumnIndex("thanhtien")));
                    list.add(hoadonchitiet);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu chi tiết hóa đơn từ cơ sở dữ liệu", e);
        }
        return list;
    }
    public boolean updateChiTietHoaDon(int maChiTietHoaDon, int maHoaDon, int maSanPham, int soLuong, int donGia, int thanhTien) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("mahoadon", maHoaDon);
            values.put("masanpham", maSanPham);
            values.put("soluong", soLuong);
            values.put("dongia", donGia);
            values.put("thanhtien", thanhTien);

            int rowsAffected = db.update("CHITIETHOADON", values, "machitiethoadon = ?", new String[]{String.valueOf(maChiTietHoaDon)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật chi tiết hóa đơn trong cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean deleteChiTietHoaDon(int maChiTietHoaDon) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            int rowsAffected = db.delete("CHITIETHOADON", "machitiethoadon = ?", new String[]{String.valueOf(maChiTietHoaDon)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi xóa chi tiết hóa đơn khỏi cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
}
