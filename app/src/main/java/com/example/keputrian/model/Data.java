package com.example.keputrian.model;

public class Data{
    private String id,tanggal_mulai,tanggal_selesai,tanggal_mandi,jam_mandi;

    public Data(){

    }

    public Data(String id, String tanggal_mulai, String tanggal_selesai, String tanggal_mandi, String jam_mandi){
        this.id = id;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_selesai = tanggal_selesai;
        this.tanggal_mandi = tanggal_mandi;
        this.jam_mandi = jam_mandi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_selesai() {
        return tanggal_selesai;
    }

    public void setTanggal_selesai(String tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public String getTanggal_mandi() {
        return tanggal_mandi;
    }

    public void setTanggal_mandi(String tanggal_mandi) {
        this.tanggal_mandi = tanggal_mandi;
    }

    public String getJam_mandi() {
        return jam_mandi;
    }

    public void setJam_mandi(String jam_mandi) {
        this.jam_mandi = jam_mandi;
    }
}