package com.example.levents.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{
    static String DB_NAME = "Levents";
    static int DB_VERSION = 13;
    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
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
                db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(1,'Tung','123','Nguyen Xuan Tung','tung@gmail.com','0395257193','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
                db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(2,'Huy','123','Nguyen Trong Huy','huy@gmail.com','0395257193','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(3,'Tungac','123','Nguyen Xuan Tung','tung@gmail.com','0395257193','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO KHACHHANG(makhachhang, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhkhachhang) VALUES(4,'Huygb','123','Nguyen Trong Huy','huy@gmail.com','0395257193','HA NOI','khachhang','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");

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
                " anhnhanvien text not null)";
        db.execSQL(nhanvien);
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(1,'DAI','123','Hoang Gia Dai','dai@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(2,'PHUONG','123','Lam Quynh Phuong','phuong@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(3,'DAIAC','123','Hoang Gia Dai','dai@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        db.execSQL("INSERT INTO NHANVIEN(manhanvien, tendangnhap, matkhau,hoten, email, sodienthoai, diachi, loaitaikhoan,anhnhanvien) VALUES(4,'PHUONGBD','123','Lam Quynh Phuong','phuong@gmail.com','0395257193','HA NOI','nhanvien','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
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