package com.example.levents.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadonchitiet;

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
}
