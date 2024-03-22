package com.example.levents.Model;

public class Hoadon {
    private int mahoadon;
    private int mataikhoan;
    private String ngaydathang;
    private int tongtin;
    private String trangthai;

    public Hoadon() {
    }

    public Hoadon(int mahoadon, int mataikhoan, String ngaydathang, int tongtin, String trangthai) {
        this.mahoadon = mahoadon;
        this.mataikhoan = mataikhoan;
        this.ngaydathang = ngaydathang;
        this.tongtin = tongtin;
        this.trangthai = trangthai;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public int getTongtin() {
        return tongtin;
    }

    public void setTongtin(int tongtin) {
        this.tongtin = tongtin;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
