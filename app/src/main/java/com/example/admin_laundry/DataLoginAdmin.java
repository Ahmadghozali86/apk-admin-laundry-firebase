package com.example.admin_laundry;

public class DataLoginAdmin {
    private String Nama_Admin;
    private String Foto_Admin;
    private String key;


    public DataLoginAdmin(String nama_Admin, String foto_Admin) {
        Nama_Admin = nama_Admin;
        Foto_Admin = foto_Admin;
    }

    public String getNama_Admin() {
        return Nama_Admin;
    }

    public void setNama_Admin(String nama_Admin) {
        Nama_Admin = nama_Admin;
    }

    public String getFoto_Admin() {
        return Foto_Admin;
    }

    public void setFoto_Admin(String foto_Admin) {
        Foto_Admin = foto_Admin;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
