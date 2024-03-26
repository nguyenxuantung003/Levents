package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Giohang;

import java.util.ArrayList;

public class Giohang_DAO {
    private DBhelper dBhelper;

    public Giohang_DAO(Context context) {
        this.dBhelper = new DBhelper(context);
    }
    public ArrayList<Giohang> getDSGioHang() {
        ArrayList<Giohang> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("SELECT GIOHANG.magiohang, GIOHANG.masanpham, GIOHANG.makhachhang, GIOHANG.soluong,SANPHAM.tensanpham, SANPHAM.gia,SANPHAM.anhsanpham,SANPHAM.soluong,SANPHAM.soluongbanra FROM GIOHANG, SANPHAM WHERE GIOHANG.masanpham = SANPHAM.masanpham", null);
            if (c.getCount() != 0) {
                c.moveToFirst();
                do {
                    Giohang gioHang = new Giohang();
                    gioHang.setMaGioHang(c.getInt(0));
                    gioHang.setMaSanPham(c.getInt(1));
                    gioHang.setMaNguoiDung(c.getInt(2));
                    gioHang.setSoLuongMua(c.getInt(3));
                    gioHang.setTenSanPham(c.getString(4));
                    gioHang.setGiaSanPham(c.getInt(5));
                    gioHang.setAnhSanPham(c.getString(6));
                    gioHang.setSoLuong(c.getInt(7));
                    gioHang.setSoluongbanra(c.getInt(8));
                    list.add(gioHang);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗiiii", e);
        }
        return list;
    }
    public ArrayList<Giohang> getDanhSachGioHangByMaNguoiDung(int maNguoiDung) {
        ArrayList<Giohang> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            // Thêm điều kiện WHERE cho mã người dùng
            String query = "SELECT GIOHANG.magiohang, GIOHANG.masanpham, GIOHANG.makhachhang, GIOHANG.soluong,SANPHAM.tensanpham, SANPHAM.gia,SANPHAM.anhsanpham,SANPHAM.soluong,SANPHAM.soluongbanra " +
                    "FROM GIOHANG, SANPHAM " +
                    "WHERE GIOHANG.masanpham = SANPHAM.masanpham AND GIOHANG.makhachhang = ?";

            Cursor c = database.rawQuery(query, new String[]{String.valueOf(maNguoiDung)});
            if (c.getCount() != 0) {
                c.moveToFirst();
                do {
                    Giohang gioHang = new Giohang();
                    gioHang.setMaGioHang(c.getInt(0));
                    gioHang.setMaSanPham(c.getInt(1));
                    gioHang.setMaNguoiDung(c.getInt(2));
                    gioHang.setSoLuongMua(c.getInt(3));
                    gioHang.setTenSanPham(c.getString(4));
                    gioHang.setGiaSanPham(c.getInt(5));
                    gioHang.setAnhSanPham(c.getString(6));
                    gioHang.setSoLuong(c.getInt(7));
                    gioHang.setSoluongbanra(c.getInt(8));
                    list.add(gioHang);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗiiii", e);
        }
        return list;
    }
    public boolean insertGioHang(Giohang gioHang) {
        SQLiteDatabase da = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masanpham", gioHang.getMaSanPham());
        values.put("makhachhang", gioHang.getMaNguoiDung());
        values.put("soluong", gioHang.getSoLuongMua());
        long check = da.insert("GIOHANG", null, values);
        return check > 0;
    }
    public boolean updateGioHang(Giohang gioHang) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong", gioHang.getSoLuongMua());
        int rowsAffected = database.update("GIOHANG", values, "magiohang = ?", new String[]{String.valueOf(gioHang.getMaGioHang())});
        return rowsAffected > 0;
    }
    public boolean deleteGioHang(Giohang gioHang) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("GIOHANG", "magiohang = ?", new String[]{String.valueOf(gioHang.getMaGioHang())});
        return check > 0;
    }
    public Giohang getGioHangByMasp(int masp, int mand) {
        Giohang gioHang = null;
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT GIOHANG.magiohang, GIOHANG.mataikhoan, GIOHANG.masanpham, GIOHANG.soluong FROM GIOHANG WHERE masanpham = ? AND mataikhoan = ?", new String[]{String.valueOf(masp), String.valueOf(mand)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                gioHang = new Giohang();
                gioHang.setMaGioHang(cursor.getInt(0));
                gioHang.setMaSanPham(cursor.getInt(1));
                gioHang.setMaNguoiDung(cursor.getInt(2));
                gioHang.setSoLuongMua(cursor.getInt(3));
            }
            cursor.moveToNext();
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }
        return gioHang;
    }

    public void clearGioHang() {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.delete("GIOHANG", null, null);
        db.close();
    }
    public ArrayList<Giohang> getSelectedItems() {
        ArrayList<Giohang> selectedItems = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        try {
            Cursor c = database.rawQuery("SELECT * FROM GIOHANG WHERE selected = 1", null);
            if (c.getCount() != 0) {
                c.moveToFirst();
                do {
                    Giohang gioHang = new Giohang();
                    gioHang.setMaGioHang(c.getInt(0));
                    gioHang.setMaNguoiDung(c.getInt(1));
                    gioHang.setMaSanPham(c.getInt(2));
                    gioHang.setSoLuongMua(c.getInt(3));
                    gioHang.setSelected(true);
                    selectedItems.add(gioHang);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗiiii", e);
        }
        return selectedItems;
    }
}
