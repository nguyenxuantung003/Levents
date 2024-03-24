package com.example.levents.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadonchitiet;

import java.util.ArrayList;

public class Chitiethoadon_DAO {
    private DBhelper dbHelper;
    public Chitiethoadon_DAO(Context context) {
        this.dbHelper = new DBhelper(context);
    }
    public ArrayList<Hoadonchitiet> getChiTietDonHangByMaDonHang(int maDonHang) {
        ArrayList<Hoadonchitiet> listChiTiet = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String query = "SELECT CHITIETHOADON.machitietdonhang,CHITIETHOADON.masanpham,SANPHAM.tensanpham,HOADON.madonhang,CHITIETHOADON.soluong,CHITIETHOADON.dongia, CHITIETHOADON.thanhtien,SANPHAM.anhsanpham FROM CHITIETHOADON INNER JOIN HOADON ON CHITIETHOADON.madonhang = HOADON.madonhang INNER JOIN SANPHAM ON CHITIETHOADON.masanpham = SANPHAM.masanpham WHERE HOADON.madonhang = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maDonHang)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Hoadonchitiet chiTietDonHang = new Hoadonchitiet();
                    chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
                    chiTietDonHang.setMaSanPham(cursor.getInt(1));
                    chiTietDonHang.setTenSanPham(cursor.getString(2));
                    chiTietDonHang.setMaDonHang(cursor.getInt(3));
                    chiTietDonHang.setSoLuong(cursor.getInt(4));
                    chiTietDonHang.setDonGia(cursor.getInt(5));
                    chiTietDonHang.setThanhTien(cursor.getInt(6));
                    chiTietDonHang.setAnhsanpham(cursor.getString(7));
                    listChiTiet.add(chiTietDonHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        } finally {
            database.close();
        }
        return listChiTiet;
    }
    public boolean xoaDonHang(Hoadonchitiet donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("CHITIETHOADON", "machitietdonhang = ?", new String[]{String.valueOf(donHang.getMaChiTietDonHang())});
        return check > 0;

    }
    public boolean insertDonHangChiTiet(Hoadonchitiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madonhang", donHangChiTiet.getMaDonHang());
        values.put("masanpham", donHangChiTiet.getMaSanPham());
        values.put("soluong", donHangChiTiet.getSoLuong());
        values.put("dongia", donHangChiTiet.getDonGia());
        values.put("thanhtien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.insert("CHITIETHOADON", null, values);
        return check > 0;
    }
    public boolean updateDonHangChiTiet(Hoadonchitiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madonhang", donHangChiTiet.getMaDonHang());
        values.put("masanpham", donHangChiTiet.getMaSanPham());
        values.put("soluong", donHangChiTiet.getSoLuong());
        values.put("dongia", donHangChiTiet.getDonGia());
        values.put("thanhtien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.update("CHITIETHOADON", values, "machitietdonhang = ?", new String[]{String.valueOf(donHangChiTiet.getMaChiTietDonHang())});
        return check > 0;
    }

}
