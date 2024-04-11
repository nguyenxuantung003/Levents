package com.example.levents.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.Model.Khachhang;

import java.util.ArrayList;

public class ThongKe_DAO {
    DBhelper dBhelper;

    public ThongKe_DAO(Context context) {
        dBhelper = new DBhelper(context);
    }
    public int tongDoanhThu(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongtien) FROM HOADON WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {

            return 0;
        }
    }
    public int tongDoanhThu2(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongtien) FROM HOADON WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ? AND trangthai = ?", new String[]{ngayBatDau, ngayKetThuc, "Đã nhận hàng"});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
    public int tongDonHang(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(mahoadon) FROM HOADON WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
    public int tongDonHang2(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(mahoadon) FROM HOADON WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ? AND trangthai = ?", new String[]{ngayBatDau, ngayKetThuc, "Đã nhận hàng"});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
    public ArrayList<Hoadonchitiet> getTop3() {
        ArrayList<Hoadonchitiet> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SANPHAM.masanpham, SANPHAM.tensanpham,SANPHAM.anhsanpham, SUM(CHITIETHOADON.soluong) AS soluongban\n" +
                "FROM SANPHAM\n" +
                "JOIN CHITIETHOADON ON SANPHAM.masanpham = CHITIETHOADON.masanpham\n" +
                "GROUP BY SANPHAM.masanpham\n" +
                "ORDER BY soluongban DESC\n" +
                "LIMIT 3;", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Hoadonchitiet(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }

        return list;
    }
    public ArrayList<Hoadonchitiet> getTop32() {
        ArrayList<Hoadonchitiet> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SANPHAM.masanpham, SANPHAM.tensanpham, SANPHAM.anhsanpham, SUM(CHITIETHOADON.soluong) AS soluongban\n" +
                "FROM SANPHAM\n" +
                "JOIN CHITIETHOADON ON SANPHAM.masanpham = CHITIETHOADON.masanpham\n" +
                "JOIN HOADON ON CHITIETHOADON.mahoadon = HOADON.mahoadon\n" +
                "WHERE HOADON.trangthai = 'Đã nhận hàng'\n" +
                "GROUP BY SANPHAM.masanpham\n" +
                "ORDER BY soluongban DESC\n" +
                "LIMIT 3;", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Hoadonchitiet(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }

        return list;
    }
    @SuppressLint("Range")
    public ArrayList<Khachhang> getTop3Khachhang() {
        ArrayList<Khachhang> top3Customers = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();

        String query = "SELECT KHACHHANG.hoten, KHACHHANG.anhkhachhang, COUNT(HOADON.mahoadon) AS soLuongDonHang " +
                "FROM KHACHHANG " +
                "JOIN HOADON ON KHACHHANG.makhachhang = HOADON.makhachhang " +
                "GROUP BY KHACHHANG.makhachhang " +
                "ORDER BY soLuongDonHang DESC " +
                "LIMIT 3";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("hoten"));
                String anhKhachHang = cursor.getString(cursor.getColumnIndex("anhkhachhang"));
                int soLuongDonHang = cursor.getInt(cursor.getColumnIndex("soLuongDonHang"));

                Khachhang khachHang = new Khachhang(tenKhachHang, anhKhachHang, soLuongDonHang);
                top3Customers.add(khachHang);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return top3Customers;
    }
    @SuppressLint("Range")
    public ArrayList<Khachhang> getTop3Khachhang2() {
        ArrayList<Khachhang> top3Customers = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();

        String query = "SELECT KHACHHANG.hoten, KHACHHANG.anhkhachhang, COUNT(HOADON.mahoadon) AS soLuongDonHang " +
                "FROM KHACHHANG " +
                "JOIN HOADON ON KHACHHANG.makhachhang = HOADON.makhachhang " +
                "WHERE HOADON.trangthai = 'Đã nhận hàng' " +
                "GROUP BY KHACHHANG.makhachhang " +
                "ORDER BY soLuongDonHang DESC " +
                "LIMIT 3";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("hoten"));
                String anhKhachHang = cursor.getString(cursor.getColumnIndex("anhkhachhang"));
                int soLuongDonHang = cursor.getInt(cursor.getColumnIndex("soLuongDonHang"));

                Khachhang khachHang = new Khachhang(tenKhachHang, anhKhachHang, soLuongDonHang);
                top3Customers.add(khachHang);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return top3Customers;
    }
}
