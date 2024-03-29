package com.example.levents.Model;

public class QLsanpham {

private int masp;
private String tensp;
private int giasp;
private int malsp;
private String tenloaisp;
private String moto;
private String anhsp;
private  int soluong;
private  int soluongbanra;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getMalsp() {
        return malsp;
    }

    public void setMalsp(int malsp) {
        this.malsp = malsp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluongbanra() {
        return soluongbanra;
    }

    public void setSoluongbanra(int soluongbanra) {
        this.soluongbanra = soluongbanra;
    }

    public QLsanpham() {
    }

    public QLsanpham(int masp, String tensp, int giasp, int malsp, String tenloaisp, String moto, String anhsp, int soluong, int soluongbanra) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.malsp = malsp;
        this.tenloaisp = tenloaisp;
        this.moto = moto;
        this.anhsp = anhsp;
        this.soluong = soluong;
        this.soluongbanra = soluongbanra;
    }
}
