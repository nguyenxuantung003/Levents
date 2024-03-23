package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Khachhang;

import java.util.ArrayList;

public class Khachang_DAO {
    private final DBhelper dbHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Khachang_DAO(Context context) {
        this.dbHelper = new DBhelper(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("NGUOIDUNG", context.MODE_PRIVATE);
        } else {
            // Xử lý khi context là null, có thể thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.e(TAG, "Context is null in NguoiDungDao constructor");
        }

    }

    public ArrayList<Khachhang> getAllNguoiDung() {

        ArrayList<Khachhang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Khachhang nguoiDung = new Khachhang();
                    nguoiDung.setMakhachhang(cursor.getInt(0));
                    nguoiDung.setTendangnhap(cursor.getString(1));
                    nguoiDung.setMatkhau(cursor.getString(2));
                    nguoiDung.setHoten(cursor.getString(3));
                    nguoiDung.setEmail(cursor.getString(4));
                    nguoiDung.setSodienthoai(cursor.getString(5));
                    nguoiDung.setDiachi(cursor.getString(6));
                    nguoiDung.setLoaitaikhoan(cursor.getString(7));
                    nguoiDung.setAnhkhachhang(cursor.getString(8));
                    list.add(nguoiDung);
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
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM KHACHHANG WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                editor = sharedPreferences.edit();
                editor.putInt("mataikhoan", cursor.getInt(0));
                editor.putString("tendangnhap", cursor.getString(1));
                editor.putString("matkhau", cursor.getString(2));
                editor.putString("hoten", cursor.getString(3));
                editor.putString("email", cursor.getString(4));
                editor.putString("sodienthoai", cursor.getString(5));
                editor.putString("diachi", cursor.getString(6));
                editor.putInt("sotien", cursor.getInt(7));
                editor.putString("loaitaikhoan", cursor.getString(8));
                editor.putString("anhtaikhoan", cursor.getString(9));
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

    public boolean checkDangKy(Khachhang nguoiDung) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendangnhap", nguoiDung.getTendangnhap());
        values.put("matkhau", nguoiDung.getMatkhau());
        values.put("hoten", nguoiDung.getHoten());
        values.put("email", nguoiDung.getEmail());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("diachi", nguoiDung.getDiachi());
        values.put("loaitaikhoan", nguoiDung.getLoaitaikhoan());
        values.put("anhtaikhoan", nguoiDung.getAnhkhachhang());
        long result = db.insert("TAIKHOAN", null, values);
        return result != -1;
    }

    public boolean tenDangNhapDaTonTai(String tenDangNhap) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM KHACHHANG WHERE tendangnhap =?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});
        boolean tonTai = cursor.getCount() > 0;
        cursor.close();
        return tonTai;
    }

    public boolean xoaNguoiDung(Khachhang nd) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("KHACHHANG", "mataikhoan = ?", new String[]{String.valueOf(nd.getMakhachhang())});
        return check > 0;
    }

    public boolean update(int manguoidung, String tennguoidung, int sotien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", tennguoidung);
        values.put("sotien", sotien);
        long check = db.update("KHACHHANG", values, "mataikhoan = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatekhachhang(Khachhang nguoiDung) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhtaikhoan", nguoiDung.getAnhkhachhang());
        values.put("hoten", nguoiDung.getHoten());
        values.put("tendangnhap", nguoiDung.getTendangnhap());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("matkhau", nguoiDung.getMatkhau());
        values.put("email", nguoiDung.getEmail());
        values.put("diachi", nguoiDung.getDiachi());
        long check = db.update("KHACHHANG", values, "mataikhoan = ?", new String[]{String.valueOf(nguoiDung.getMakhachhang())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateSoTien(int maTaiKhoan, int soTienMoi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien", soTienMoi);

        long result = db.update("KHACHHANG", values, "mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

        return result != -1;
    }
    public Khachhang getNguoiDungByMaTaiKhoan(int maTaiKhoan) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Khachhang nguoiDung = null;

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM KHACHHANG WHERE mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                nguoiDung = new Khachhang();
                nguoiDung.setMakhachhang(cursor.getInt(0));
                nguoiDung.setTendangnhap(cursor.getString(1));
                nguoiDung.setMatkhau(cursor.getString(2));
                nguoiDung.setHoten(cursor.getString(3));
                nguoiDung.setEmail(cursor.getString(4));
                nguoiDung.setSodienthoai(cursor.getString(5));
                nguoiDung.setDiachi(cursor.getString(6));
                nguoiDung.setLoaitaikhoan(cursor.getString(7));
                nguoiDung.setAnhkhachhang(cursor.getString(8));
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }

        return nguoiDung;
    }

}
