package com.example.levents.Model;

public class Giohang {
    private int maGioHang;
    private int maSanPham;
    private int maNguoiDung;

    private int soLuongMua;
    private String tenSanPham;
    private int giaSanPham;
    private boolean isSelected;
    private String anhSanPham;
    private int soLuong;
    private int soluongbanra;

    public Giohang() {
    }
    public Giohang(int maSanPham, int maNguoiDung, int soLuongMua) {
        this.maSanPham = maSanPham;
        this.maNguoiDung = maNguoiDung;
        this.soLuongMua = soLuongMua;
    }

    public Giohang(int maGioHang, int maSanPham, int maNguoiDung, int soLuongMua, String tenSanPham, int giaSanPham, boolean isSelected, String anhSanPham, int soLuong, int soluongbanra) {
        this.maGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.maNguoiDung = maNguoiDung;
        this.soLuongMua = soLuongMua;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.isSelected = isSelected;
        this.anhSanPham = anhSanPham;
        this.soLuong = soLuong;
        this.soluongbanra = soluongbanra;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoluongbanra() {
        return soluongbanra;
    }

    public void setSoluongbanra(int soluongbanra) {
        this.soluongbanra = soluongbanra;
    }
}
