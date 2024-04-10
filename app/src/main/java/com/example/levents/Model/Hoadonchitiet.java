package com.example.levents.Model;

public class Hoadonchitiet {
    private int maChiTietDonHang;
    private int maDonHang;
    private int maSanPham;
    private String tenSanPham;
    private int soLuong;
    private int donGia;
    private int thanhTien;
    private String anhsanpham;

    public Hoadonchitiet() {
    }
    public Hoadonchitiet(int maSanPham, String tenSanPham, String anhSanPham, int soLuongBan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.anhsanpham= anhSanPham;
        this.soLuong = soLuongBan;
    }


    public Hoadonchitiet(int maDonHang, int maSanPham, int soLuong, int donGia, int thanhTien) {
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public Hoadonchitiet(int maSanPham, String tenSanPham, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public Hoadonchitiet(int maChiTietDonHang, int maDonHang, int maSanPham, String tenSanPham, int soLuong, int donGia, int thanhTien) {
        this.maChiTietDonHang = maChiTietDonHang;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(int maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }
}
