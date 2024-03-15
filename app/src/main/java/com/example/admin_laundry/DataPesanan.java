package com.example.admin_laundry;

public class DataPesanan {
    private String Nama_Pelanggan;
    private String Nama_Admin;
    private Double BeratKg;
    private Double Biaya_Cuci;
    private Double Saldo_Pembelian;
    private Double Saldo_Kembalian;
    private String Key;

    public DataPesanan() {

    }

    public DataPesanan(String nama_Pelanggan, String nama_Admin, Double beratKg, Double biaya_Cuci, Double saldo_Pembelian, Double saldo_Kembalian) {
        Nama_Pelanggan = nama_Pelanggan;
        Nama_Admin = nama_Admin;
        BeratKg = beratKg;
        Biaya_Cuci = biaya_Cuci;
        Saldo_Pembelian = saldo_Pembelian;
        Saldo_Kembalian = saldo_Kembalian;
    }

    public String getNama_Pelanggan() {
        return Nama_Pelanggan;
    }

    public void setNama_Pelanggan(String nama_Pelanggan) {
        Nama_Pelanggan = nama_Pelanggan;
    }

    public String getNama_Admin() {
        return Nama_Admin;
    }

    public void setNama_Admin(String nama_Admin) {
        Nama_Admin = nama_Admin;
    }

    public Double getBeratKg() {
        return BeratKg;
    }

    public void setBeratKg(Double beratKg) {
        BeratKg = beratKg;
    }

    public Double getBiaya_Cuci() {
        return Biaya_Cuci;
    }

    public void setBiaya_Cuci(Double biaya_Cuci) {
        Biaya_Cuci = biaya_Cuci;
    }

    public Double getSaldo_Pembelian() {
        return Saldo_Pembelian;
    }

    public void setSaldo_Pembelian(Double saldo_Pembelian) {
        Saldo_Pembelian = saldo_Pembelian;
    }

    public Double getSaldo_Kembalian() {
        return Saldo_Kembalian;
    }

    public void setSaldo_Kembalian(Double saldo_Kembalian) {
        Saldo_Kembalian = saldo_Kembalian;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}