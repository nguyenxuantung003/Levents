package com.example.levents.Model;

public class Sanpham {
    private int masanpham;
    private String tensanpham;
    private int gia;
    private String mota;;
    private String anhsanpham;
    private int soluong;
    private int soluotbanra;
    public Sanpham(int masanpham, String tensanpham, int gia, String mota, String anhSanPham,int soluong,int soluotbanra) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.mota = mota;
        this.anhsanpham = anhSanPham;
        this.soluong = soluong;
        this.soluotbanra = soluotbanra;
    }

    public Sanpham(String tensanpham, int giasanpham, int soluongsanpham, String motasanpham, String anhsanpham, int soluongbanra) {
        this.tensanpham = tensanpham;
        this.gia = giasanpham;
        this.soluong = soluongsanpham;
        this.mota = motasanpham;
        this.anhsanpham = anhsanpham;
        this.soluotbanra = soluongbanra;

    }



    public int getSoluotbanra() {
        return soluotbanra;
    }

    public void setSoluotbanra(int soluotbanra) {
        this.soluotbanra = soluotbanra;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
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
