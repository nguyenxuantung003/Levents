package com.example.levents.Model;

public class Nhanvien {
    private int manhanvien;
    private String tendangnhap;
    private String matkhau;
    private String hoten;
    private String email;
    private String diachi;
    private String sodienthoai;
    private String anhnhanvien;
    private String loaitaikhoan;

    public Nhanvien() {
    }

    public Nhanvien(int manhanvien, String tendangnhap, String matkhau, String hoten, String email, String diachi, String sodienthoai, String anhnhanvien, String loaitaikhoan) {
        this.manhanvien = manhanvien;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.email = email;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.anhnhanvien = anhnhanvien;
        this.loaitaikhoan = loaitaikhoan;
    }

    public Nhanvien(String tenDangNhap, String matKhau, String hoTen, String email, String sdt, String diaChi, String anh, String loaitaikhoan) {
        this.tendangnhap = tenDangNhap;
        this.matkhau = matKhau;
        this.hoten = hoTen;
        this.email = email;
        this.sodienthoai = sdt;
        this.diachi = diaChi;
        this.anhnhanvien = anh;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
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

    public String getAnhnhanvien() {
        return anhnhanvien;
    }

    public void setAnhnhanvien(String anhnhanvien) {
        this.anhnhanvien = anhnhanvien;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
