package com.example.levents.Model;

public class Hoadon {
    private int maDonHang;
    private int maTaiKhoan;
    private String tenTaiKhoan;
    private int manhanvien;

    public Hoadon(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    private String ngayDatHang;
    private int tongTien;
    private String trangthai;
    private String tennhanvien;

    public Hoadon(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public Hoadon(int maDonHang, int maTaiKhoan, String tenTaiKhoan, int manhanvien, String ngayDatHang, int tongTien, String trangthai, String tennhanvien) {
        this.maDonHang = maDonHang;
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.manhanvien = manhanvien;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
        this.tennhanvien = tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public Hoadon() {
    }
    public Hoadon(int maTaiKhoan, String ngayDatHang,int tongTien, String trangthai) {
        this.maTaiKhoan = maTaiKhoan;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

    public Hoadon(int maDonHang, int maTaiKhoan, String tenTaiKhoan, String ngayDatHang, int tongTien, String trangthai) {
        this.maDonHang = maDonHang;
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
