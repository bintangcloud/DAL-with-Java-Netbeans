/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
/**
 *
 * @author binta
 */
public class TransaksiRuangan {
    private String idTransaksi;
    private String idRuangan;
    private Time waktuMulai;
    private Time waktuSelesai;
    
    // Relationship
    private Ruangan ruangan;
    
    public TransaksiRuangan() {}
    
    public TransaksiRuangan(String idTransaksi, String idRuangan, 
                           Time waktuMulai, Time waktuSelesai) {
        this.idTransaksi = idTransaksi;
        this.idRuangan = idRuangan;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }
    
    public String getIdTransaksi() {
        return idTransaksi;
    }
    
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    
    public String getIdRuangan() {
        return idRuangan;
    }
    
    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }
    
    public Time getWaktuMulai() {
        return waktuMulai;
    }
    
    public void setWaktuMulai(Time waktuMulai) {
        this.waktuMulai = waktuMulai;
    }
    
    public Time getWaktuSelesai() {
        return waktuSelesai;
    }
    
    public void setWaktuSelesai(Time waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }
    
    public Ruangan getRuangan() {
        return ruangan;
    }
    
    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }
    
    // Menghitung Durasi (Jam)
    public long getDurasiJam() {
        if (waktuMulai != null && waktuSelesai != null) {
            long diff = waktuSelesai.getTime() - waktuMulai.getTime();
            return diff / (1000 * 60 * 60); // Konversi Milidetik ke Jam
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Transaksi: " + idTransaksi + 
               ", Ruangan: " + idRuangan + 
               ", Waktu: " + waktuMulai + " - " + waktuSelesai;
    }
}
