package com.example.levents.Model;

public class Loaisanpham {
    private int maloaisanpham;
    private String tenloaisanpham;

    public Loaisanpham() {
    }

    public Loaisanpham(int maloaisanpham, String tenloaisanpham) {
        this.maloaisanpham = maloaisanpham;
        this.tenloaisanpham = tenloaisanpham;
    }

    public int getMaloaisanpham() {
        return maloaisanpham;
    }

    public void setMaloaisanpham(int maloaisanpham) {
        this.maloaisanpham = maloaisanpham;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }
}
