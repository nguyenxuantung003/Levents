package com.example.levents.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DBhelper extends SQLiteOpenHelper{
    static String DB_NAME = "Levents";
    static int DB_VERSION = 001;

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String khachhang = "CREATE TABLE KHACHHANG(" +
                "makhachhang integer primary key autoincrement," +
                " tendangnhap text not null," +
                " matkhau text not null," +
                " hoten text not null," +
                " email text not null," +
                " sodienthoai text not null," +
                " diachi text not null," +
                "loaitaikhoan text not null," +
                " anhkhachhang text not null)";
                db.execSQL(khachhang);

        String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                "maloaisanpham integer primary key autoincrement," +
                " tenloaisanpham text not null)";
                db.execSQL(loaiSanPham);

        String sanpham = "CREATE TABLE SANPHAM(" +
                " masanpham integer primary key autoincrement," +
                " tensanpham text not null," +
                " gia integer not null," +
                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
                " mota text not null," +
                " anhsanpham text not null," +
                " soluong integer not null)" ;

                db.execSQL(sanpham);

        String giohang = "CREATE TABLE GIOHANG(" +
                "magiohang integer primary key autoincrement, " +
                "mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " soluong integer not null)";
        db.execSQL(giohang);

        String hoadon = "CREATE TABLE HOADON(" +
                " mahoadon integer primary key autoincrement," +
                " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " ngaydathang text not null," +
                " tongtien integer not null," +
                " trangthai text not null)";
        db.execSQL(hoadon);

        String chitiethoadon = "CREATE TABLE CHITIETHOADON(" +
                "machitiethoadon integer primary key autoincrement," +
                " mahoadon integer REFERENCES DONHANG(madonhang)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                "soluong integer not null," +
                " dongia integer not null," +
                " thanhtien integer not null)";
        db.execSQL(chitiethoadon);

        String nhanvien = "CREATE TABLE NHANVIEN(" +
                "manhanvien integer primary key autoincrement," +
                " tendangnhap text not null," +
                " matkhau text not null," +
                " hoten text not null," +
                " email text not null," +
                " sodienthoai text not null," +
                " diachi text not null," +
                "loaitaikhoan text not null," +
                " anhtaikhoan text not null)";
        db.execSQL(nhanvien);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            db.execSQL("DROP TABLE IF EXISTS GIOHANG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS CHITIETHOADON");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            onCreate(db);

        }
    }
}