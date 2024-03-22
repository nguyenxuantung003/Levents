package com.example.levents.Model;

public class Hoadonchitiet {
    private int machitiethoadon;
    private int mahoadon;
    private int masanpham;
    private int soluong;
    private int dongia;
    private int thanhtien;

    public Hoadonchitiet() {
    }

    public Hoadonchitiet(int machitiethoadon, int mahoadon, int masanpham, int soluong, int dongia, int thanhtien) {
        this.machitiethoadon = machitiethoadon;
        this.mahoadon = mahoadon;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public int getMachitiethoadon() {
        return machitiethoadon;
    }

    public void setMachitiethoadon(int machitiethoadon) {
        this.machitiethoadon = machitiethoadon;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
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

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}
