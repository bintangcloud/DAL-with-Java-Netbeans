/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author binta
 */
public class Transaksi {
    private String idTransaksi;
    private Date tanggalTransaksi;
    private String idPelanggan;
    private String idKasir;
    private String idPembayaran;
    private String tokenWifi;
    
    // Relationship
    private Pelanggan pelanggan;
    private Kasir kasir;
    private Pembayaran pembayaran;
    private List<TransaksiRuangan> listRuangan;
    private List<TransaksiFnb> listFnb;
    
    // Constructors
    public Transaksi() {}
    
    public Transaksi(String idTransaksi, Date tanggalTransaksi, String idPelanggan, 
                     String idKasir, String idPembayaran, String tokenWifi) {
        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.idPelanggan = idPelanggan;
        this.idKasir = idKasir;
        this.idPembayaran = idPembayaran;
        this.tokenWifi = tokenWifi;
    }
    
    // Getters and Setters
    public String getIdTransaksi() {
        return idTransaksi;
    }
    
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    
    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }
    
    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
    
    public String getIdPelanggan() {
        return idPelanggan;
    }
    
    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    
    public String getIdKasir() {
        return idKasir;
    }
    
    public void setIdKasir(String idKasir) {
        this.idKasir = idKasir;
    }
    
    public String getIdPembayaran() {
        return idPembayaran;
    }
    
    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }
    
    public String getTokenWifi() {
        return tokenWifi;
    }
    
    public void setTokenWifi(String tokenWifi) {
        this.tokenWifi = tokenWifi;
    }
    
    // Relationship getters and setters
    public Pelanggan getPelanggan() {
        return pelanggan;
    }
    
    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }
    
    public Kasir getKasir() {
        return kasir;
    }
    
    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }
    
    public Pembayaran getPembayaran() {
        return pembayaran;
    }
    
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }
    
    public List<TransaksiRuangan> getListRuangan() {
        return listRuangan;
    }
    
    public void setListRuangan(List<TransaksiRuangan> listRuangan) {
        this.listRuangan = listRuangan;
    }
    
    public List<TransaksiFnb> getListFnb() {
        return listFnb;
    }
    
    public void setListFnb(List<TransaksiFnb> listFnb) {
        this.listFnb = listFnb;
    }
    
    @Override
    public String toString() {
        return idTransaksi + " - " + 
               (tanggalTransaksi != null ? tanggalTransaksi.toString() : "") + 
               " - Pelanggan: " + idPelanggan;
    }
}
