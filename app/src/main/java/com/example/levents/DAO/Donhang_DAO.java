package com.example.levents.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Hoadon;

import java.util.ArrayList;

public class Donhang_DAO {
    private DBhelper dbHelper;

    public Donhang_DAO(Context context) {
        this.dbHelper = new DBhelper(context);
    }
    public ArrayList<Hoadon> getDsDonHang() {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT HOADON.madonhang, KHACHHANG.mataikhoan, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai " +
                    "FROM HOADON,KHACHHANG WHERE HOADON.mataikhoan = KHACHHANG.mataikhoan", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                   Hoadon donHang = new Hoadon();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setMaTaiKhoan(cursor.getInt(1));
                    donHang.setTenTaiKhoan(cursor.getString(2));
                    donHang.setNgayDatHang(cursor.getString(3));
                    donHang.setTongTien(cursor.getInt(4));
                    donHang.setTrangthai(cursor.getString(5));

                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }
    public int xoaDonHang(int madonhang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CHITIETHOADON where madonhang = ?", new String[]{String.valueOf(madonhang)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("HOADON", "madonhang = ?", new String[]{String.valueOf(madonhang)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
    public boolean updateDonHang(Hoadon donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mataikhoan", donHang.getMaTaiKhoan());
        values.put("trangthai", donHang.getTrangthai());
        long check = sqLiteDatabase.update("HOADON", values, "madonhang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
        return check > 0;

    }
    public int insertDonHang(Hoadon donHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mataikhoan", donHang.getMaTaiKhoan());
        values.put("ngaydathang", donHang.getNgayDatHang());
        values.put("tongtien", donHang.getTongTien());
        values.put("trangthai", donHang.getTrangthai());

        try {
            long insertedId = db.insert("HOADON", null, values);
            db.close();

            // Kiểm tra xem đơn hàng đã được chèn thành công hay không
            if (insertedId > 0) {
                return (int) insertedId; // Trả về ID của đơn hàng nếu thành công
            } else {
                return -1; // Trả về -1 nếu có lỗi khi chèn đơn hàng
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn đơn hàng", e);
            return -1; // Trả về -1 nếu có lỗi khi chèn đơn hàng
        }
    }
    public ArrayList<Hoadon> getDonHangByMaTaiKhoan(int maTaiKhoan) {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String query = "SELECT HOADON.madonhang, KHACHHANG.mataikhoan, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai FROM HOADON JOIN KHACHHANG ON HOADON.mataikhoan = KHACHHANG.mataikhoan WHERE KHACHHANG.mataikhoan = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Hoadon donHang = new Hoadon();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setMaTaiKhoan(cursor.getInt(1));
                    donHang.setTenTaiKhoan(cursor.getString(2));
                    donHang.setNgayDatHang(cursor.getString(3));
                    donHang.setTongTien(cursor.getInt(4));
                    donHang.setTrangthai(cursor.getString(5));
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }


}
