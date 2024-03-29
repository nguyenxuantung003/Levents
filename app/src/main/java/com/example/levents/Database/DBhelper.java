package com.example.levents.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    static String DB_NAME = "Levents";
    static int DB_VERSION = 19;

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
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(1,'Tung','123','Nguyen Xuan Tung','tung1@gmail.com','0395257191','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(2,'Huy','123','Nguyen Trong Huy','huy2@gmail.com','0395257192','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(3,'Tungac','123','Nguyen Xuan Tunga','tung3@gmail.com','0395257193','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(4,'Huygb','123','Nguyen Trong Huyb','huy4@gmail.com','0395257194','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");

        String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                "maloaisanpham integer primary key autoincrement," +
                " tenloaisanpham text not null)";
        db.execSQL(loaiSanPham);
        db.execSQL("INSERT INTO LOAISANPHAM VALUES(1,'quan')");
        db.execSQL("INSERT INTO LOAISANPHAM VALUES(2,'ao')");
        db.execSQL("INSERT INTO LOAISANPHAM VALUES(3,'giay')");
        db.execSQL("INSERT INTO LOAISANPHAM VALUES(4,'tat')");

        String sanpham = "CREATE TABLE SANPHAM(" +
                " masanpham integer primary key autoincrement," +
                " tensanpham text not null," +
                " gia integer not null," +
                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
                " mota text not null," +
                " anhsanpham text not null," +
                " soluong integer not null," +
                " soluongbanra integer not null)";
        db.execSQL(sanpham);
        db.execSQL("INSERT INTO SANPHAM VALUES(1,'Quan 01',100000,1,'Quan dep vai l','https://bizweb.dktcdn.net/100/346/633/files/thuc-an-kho-cho-cho-meo-ra-doi-nhu-the-nao.jpg?v=1553479214146',12,0)");
        db.execSQL("INSERT INTO SANPHAM VALUES(2,'Quan 2',200000,1,'Quan dep vai l','https://pethouse.com.vn/wp-content/uploads/2023/01/ezgif-5-1e317ae8fd-800x800.jpg',10,0)");
        db.execSQL("INSERT INTO SANPHAM VALUES(3,'Quan 3',300000,1,'Quan dep vai l','https://bizweb.dktcdn.net/100/091/443/products/hieuunganh-com-5e918dd032c21.png?v=1586597434450',10,0)");
        db.execSQL("INSERT INTO SANPHAM VALUES(4,'Quan 4',400000,1,'Quan dep vai l','https://www.petmart.vn/wp-content/uploads/2021/06/thuc-an-cho-cho-poodle-con-royal-canin-poodle-puppy2.jpg',10,0)");
        db.execSQL("INSERT INTO SANPHAM VALUES(5,'Quan 5',500000,1,'Quan dep vai l','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUpL9qDcvaqEL85UX1lrU8RHRkD2AAnpcWCw&usqp=CAU',0,0)");


        String giohang = "CREATE TABLE GIOHANG(" +
                "magiohang integer primary key autoincrement, " +
                "makhachhang integer REFERENCES KHACHHANG(makhachhang)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " soluong integer not null)";
        db.execSQL(giohang);
        db.execSQL("INSERT INTO GIOHANG VALUES(1,1,2,3)");
        db.execSQL("INSERT INTO GIOHANG VALUES(2,2,1,2)");
        db.execSQL("INSERT INTO GIOHANG VALUES(3,3,4,1)");
        db.execSQL("INSERT INTO GIOHANG VALUES(4,4,3,2)");
        db.execSQL("INSERT INTO GIOHANG VALUES(5,5,5,3)");


        String hoadon = "CREATE TABLE HOADON(" +
                " mahoadon integer primary key autoincrement," +
                " makhachhang integer REFERENCES KHACHHANG(makhachhang)," +
                " ngaydathang text not null," +
                " tongtien integer not null," +
                " trangthai text not null)";
        db.execSQL(hoadon);
        db.execSQL("INSERT INTO HOADON VALUES(1,1,'16/11/2023',100000,'Đã nhận hàng')");
        db.execSQL("INSERT INTO HOADON VALUES(2,2,'16/12/2023',200000,'Đã nhận hàng')");
        db.execSQL("INSERT INTO HOADON VALUES(3,3,'17/09/2023',300000,'Đã nhận hàng')");
        db.execSQL("INSERT INTO HOADON VALUES(4,4,'18/01/2023',400000,'Đã nhận hàng')");
        db.execSQL("INSERT INTO HOADON VALUES(5,2,'19/11/2023',50000,'Đã nhận hàng')");

        String chitiethoadon = "CREATE TABLE CHITIETHOADON(" +
                "machitiethoadon integer primary key autoincrement," +
                " mahoadon integer REFERENCES HOADON(mahoadon)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                "soluong integer not null," +
                " dongia integer not null," +
                " thanhtien integer not null)";
        db.execSQL(chitiethoadon);
        db.execSQL("INSERT INTO CHITIETHOADON VALUES(1,2,5,5,20,20)");
        db.execSQL("INSERT INTO CHITIETHOADON VALUES(2,2,1,4,30,30)");
        db.execSQL("INSERT INTO CHITIETHOADON VALUES(3,3,2,3,30,30)");
        db.execSQL("INSERT INTO CHITIETHOADON VALUES(4,2,3,2,30,30)");
        db.execSQL("INSERT INTO CHITIETHOADON VALUES(5,3,5,5,10,10)");

        String nhanvien = "CREATE TABLE NHANVIEN(" +
                "manhanvien integer primary key autoincrement," +
                " tendangnhap text not null," +
                " matkhau text not null," +
                " hoten text not null," +
                " email text not null," +
                " sodienthoai text not null," +
                " diachi text not null," +
                "loaitaikhoan text not null," +
                " anhnhanvien text not null)";
        db.execSQL(nhanvien);
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(1,'DAI','123','Hoang Gia Dai','dai@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(2,'PHUONG','123','Lam Quynh Phuong','phuong@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(3,'DAIAC','123','Hoang Gia Dai','dai@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(4,'PHUONGBD','123','Lam Quynh Phuong','phuong@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
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

    public boolean isTableEmpty() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN ", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close(); // Đóng con trỏ khi không cần nữa
            return count == 0; // Trả về true nếu bảng rỗng, ngược lại trả về false
        }
        return true; // Trả về true nếu không thể truy vấn hoặc con trỏ là null
    }
}