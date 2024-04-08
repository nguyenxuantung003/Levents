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

    public ArrayList<Hoadon> getDsDonHang2() {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai, NHANVIEN.hoten " +
                    "FROM HOADON " +
                    "INNER JOIN KHACHHANG ON HOADON.makhachhang = KHACHHANG.makhachhang " +
                    "LEFT JOIN NHANVIEN ON HOADON.manhanvien = NHANVIEN.manhanvien", null);
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
                    donHang.setTennhanvien(cursor.getString(6)); // Thêm tên nhân viên vào đơn hàng
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        for (Hoadon donHang : list) {
            Log.d("HoaDon_DAO", "Mã đơn hàng: " + donHang.getMaDonHang());
            Log.d("HoaDon_DAO", "Mã khách hàng: " + donHang.getMaTaiKhoan());
            Log.d("HoaDon_DAO", "Tên khách hàng: " + donHang.getTenTaiKhoan());
            Log.d("HoaDon_DAO", "Ngày đặt hàng: " + donHang.getNgayDatHang());
            Log.d("HoaDon_DAO", "Tổng tiền: " + donHang.getTongTien());
            Log.d("HoaDon_DAO", "Trạng thái: " + donHang.getTrangthai());
            Log.d("HoaDon_DAO", "Tên nhân viên: " + donHang.getTennhanvien());
            Log.d("HoaDon_DAAO)", "----------------------------------");
        }
        return list;
    }
    public ArrayList<Hoadon> getDsDonHang() {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai " +
                    "FROM HOADON,KHACHHANG WHERE HOADON.makhachhang = KHACHHANG.makhachhang", null);
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
        long check = db.delete("HOADON", "mahoadon = ?", new String[]{String.valueOf(madonhang)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
    public boolean updateDonHang(Hoadon donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("makhachhang", donHang.getMaTaiKhoan());
        values.put("trangthai", donHang.getTrangthai());
        values.put("manhanvien", donHang.getManhanvien());
        long check = sqLiteDatabase.update("HOADON", values, "mahoadon = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
        return check > 0;

    }
    public int insertDonHang(Hoadon donHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("makhachhang", donHang.getMaTaiKhoan());
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
            String query = "SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai FROM HOADON JOIN KHACHHANG ON HOADON.makhachhang = KHACHHANG.makhachhang WHERE KHACHHANG.makhachhang = ?";
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
                    Log.d("CursorData", "MaDonHang: " + cursor.getInt(0) + ", MaTaiKhoan: " + cursor.getInt(1) + ", TenTaiKhoan: " + cursor.getString(2) + ", NgayDatHang: " + cursor.getString(3) + ", TongTien: " + cursor.getInt(4) + ", TrangThai: " + cursor.getString(5));
                } while (cursor.moveToNext());
            } else {
                Log.d("CursorData", "No data found for maTaiKhoan: " + maTaiKhoan);
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }
    public ArrayList<Hoadon> getDsDonHangTheoNhanVien(int manhanvien) {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            String query = "SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai, NHANVIEN.hoten " +
                    "FROM HOADON " +
                    "INNER JOIN KHACHHANG ON HOADON.makhachhang = KHACHHANG.makhachhang " +
                    "LEFT JOIN NHANVIEN ON HOADON.manhanvien = NHANVIEN.manhanvien " +
                    "WHERE HOADON.manhanvien = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(manhanvien)});

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
                    String tenNhanVien = cursor.getString(6); // Lấy tên nhân viên từ cột thứ 7
                    donHang.setTennhanvien(tenNhanVien != null ? tenNhanVien : "Không có thông tin"); // Đảm bảo không null
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.d(ContentValues.TAG, "Lỗi", e);
        }
        return list;
    }
    public ArrayList<Hoadon> getDsDonHangTheoTrangThai(String trangThai) {
        ArrayList<Hoadon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            String query = "SELECT HOADON.mahoadon, KHACHHANG.makhachhang, KHACHHANG.hoten, HOADON.ngaydathang, HOADON.tongtien, HOADON.trangthai, NHANVIEN.hoten " +
                    "FROM HOADON " +
                    "INNER JOIN KHACHHANG ON HOADON.makhachhang = KHACHHANG.makhachhang " +
                    "LEFT JOIN NHANVIEN ON HOADON.manhanvien = NHANVIEN.manhanvien " +
                    "WHERE HOADON.trangthai = ?";
            Cursor cursor = database.rawQuery(query, new String[]{trangThai});

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
                    String tenNhanVien = cursor.getString(6); // Lấy tên nhân viên từ cột thứ 7
                    donHang.setTennhanvien(tenNhanVien != null ? tenNhanVien : "Không có thông tin"); // Đảm bảo không null
                    list.add(donHang);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        }
        return list;
    }



}
