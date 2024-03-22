package com.example.levents.Model;

public class Giohang {
    private int magiohang;
    private int mataikhoan;
    private int masanpham;
    private int soluong;

    public Giohang() {
    }

    public Giohang(int magiohang, int mataikhoan, int masanpham, int soluong) {
        this.magiohang = magiohang;
        this.mataikhoan = mataikhoan;
        this.masanpham = masanpham;
        this.soluong = soluong;
    }

    public int getMagiohang() {
        return magiohang;
    }

    public void setMagiohang(int magiohang) {
        this.magiohang = magiohang;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
