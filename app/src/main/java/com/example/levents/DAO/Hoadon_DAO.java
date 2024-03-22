package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadon;

import java.util.ArrayList;

public class Hoadon_DAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public boolean insertHoaDon(int maTaiKhoan, String ngayDatHang, int tongTien, String trangThai) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("mataikhoan", maTaiKhoan);
            values.put("ngaydathang", ngayDatHang);
            values.put("tongtien", tongTien);
            values.put("trangthai", trangThai);

            long newRowId = db.insert("HOADON", null, values);
            success = newRowId != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn hoadon vào cơ sở dữ liệu", e);
            db.close();
        }
        return success;
    }
    @SuppressLint("Range")
    public ArrayList<Hoadon> getAllHoaDon() {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM HOADON", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Hoadon hoaDon = new Hoadon();
                    hoaDon.setMahoadon(cursor.getInt(cursor.getColumnIndex("mahoadon")));
                    hoaDon.setMataikhoan(cursor.getInt(cursor.getColumnIndex("mataikhoan")));
                    hoaDon.setNgaydathang(cursor.getString(cursor.getColumnIndex("ngaydathang")));
                    hoaDon.setTongtin(cursor.getInt(cursor.getColumnIndex("tongtien")));
                    hoaDon.setTrangthai(cursor.getString(cursor.getColumnIndex("trangthai")));
                    list.add(hoaDon);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu hóa đơn từ cơ sở dữ liệu", e);
        }
        return list;
    }
    public boolean updateHoaDon(int maHoaDon, int maTaiKhoan, String ngayDatHang, int tongTien, String trangThai) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("mataikhoan", maTaiKhoan);
            values.put("ngaydathang", ngayDatHang);
            values.put("tongtien", tongTien);
            values.put("trangthai", trangThai);

            int rowsAffected = db.update("HOADON", values, "mahoadon = ?", new String[]{String.valueOf(maHoaDon)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật hóa đơn trong cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean deleteHoaDon(int maHoaDon) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            int rowsAffected = db.delete("HOADON", "mahoadon = ?", new String[]{String.valueOf(maHoaDon)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi xóa hóa đơn khỏi cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }

}
