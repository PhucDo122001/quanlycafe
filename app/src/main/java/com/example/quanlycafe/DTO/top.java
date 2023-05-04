package com.example.quanlycafe.DTO;

public class top {
    private String tenMon;
    private int soLuongMon;

    public top() {
    }

    public top(String tenMon, int soLuongMon) {
        this.tenMon = tenMon;
        this.soLuongMon = soLuongMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuongMon() {
        return soLuongMon;
    }

    public void setSoLuongMon(int soLuongMon) {
        this.soLuongMon = soLuongMon;
    }

    @Override
    public String toString() {
        return "top{" +
                "tenMon='" + tenMon + '\'' +
                ", soLuongMon=" + soLuongMon +
                '}';
    }
}
