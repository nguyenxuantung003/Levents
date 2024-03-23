package com.example.levents.Model;

public class Sanpham {
    private int masanpham;
    private int maloaisanpham;
    private String tenloaisanpham;
    private String tensanpham;
    private int gia;
    private String mota;;
    private String anhsanpham;
    private int soluong;
    private int soluotbanra;
    public Sanpham(int masanpham, String tensanpham, int gia, int maloaisanpham, String tenloaisanpham , String mota, String anhsanpham, int soluong,int soluotbanra) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.tenloaisanpham = tenloaisanpham;
        this.mota = mota;
        this.anhsanpham = anhsanpham;
        this.soluong = soluong;
        this.soluotbanra =soluotbanra;
    }

    public Sanpham(int masanpham, String tensanpham, int gia, int maloaisanpham, String mota, String anhSanPham, int soluong,int soLuotBanRa) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.mota = mota;
        this.anhsanpham = anhSanPham;
        this.soluong = soluong;
        this.soluotbanra =soLuotBanRa;
    }

    public Sanpham(int masanpham, String tensanpham, int gia, int maloaisanpham, String tenloaisanpham, String mota, String anhSanPham) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.maloaisanpham = maloaisanpham;
        this.tenloaisanpham = tenloaisanpham;
        this.mota = mota;
        this.anhsanpham = anhSanPham;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
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
