package com.example.levents.Model;

public class Sanpham {
    private int masanpham;
    private int maloaisanpham;
    private String tensanpham;
    private int gia;
    private String mota;;
    private String anhsanpham;
    private int soluong;

    public Sanpham() {
    }

    public Sanpham(int masanpham, int maloaisanpham, String tensanpham, int gia, String mota, String anhsanpham, int soluong) {
        this.masanpham = masanpham;
        this.maloaisanpham = maloaisanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.mota = mota;
        this.anhsanpham = anhsanpham;
        this.soluong = soluong;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getMaloaisanpham() {
        return maloaisanpham;
    }

    public void setMaloaisanpham(int maloaisanpham) {
        this.maloaisanpham = maloaisanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
