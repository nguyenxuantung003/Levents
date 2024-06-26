package com.example.levents.Model;

public class Khachhang {
    private int makhachhang;
    private String tendangnhap;
    private String matkhau;
    private String hoten;
    private String email;
    private String diachi;
    private String sodienthoai;
    private String anhkhachhang;
    private String loaitaikhoan;
    private int soluongdonhang;

    public int getSoluongdonhang() {
        return soluongdonhang;
    }

    public void setSoluongdonhang(int soluongdonhang) {
        this.soluongdonhang = soluongdonhang;
    }

    public Khachhang() {
    }

    public Khachhang(int makhachhang, String tendangnhap, String matkhau, String hoten, String email, String diachi, String sodienthoai, String anhkhachhang, String loaitaikhoan) {
        this.makhachhang = makhachhang;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.email = email;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.anhkhachhang = anhkhachhang;
        this.loaitaikhoan = loaitaikhoan;
    }

    public Khachhang(String tendangnhap, String matkhau, String hoten, String email, String diachi, String sodienthoai, String anhkhachhang, String loaitaikhoan) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.email = email;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.anhkhachhang = anhkhachhang;
        this.loaitaikhoan = loaitaikhoan;
    }

    public Khachhang(String tenKhachHang, String anhKhachHang, int soLuongDonHang) {
        this.hoten = tenKhachHang;
        this.anhkhachhang = anhKhachHang;
        this.soluongdonhang = soLuongDonHang;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getAnhkhachhang() {
        return anhkhachhang;
    }

    public void setAnhkhachhang(String anhkhachhang) {
        this.anhkhachhang = anhkhachhang;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
