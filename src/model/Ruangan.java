/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
/**
 *
 * @author binta
 */
public class Ruangan {
    private String idRuangan;
    private String namaRuangan;
    private BigDecimal hargaPerJam;
    
    public Ruangan() {}
    
    public Ruangan(String idRuangan, String namaRuangan, BigDecimal hargaPerJam) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.hargaPerJam = hargaPerJam;
    }
    
    // Overloaded constructor 
    public Ruangan(String idRuangan, String namaRuangan, double hargaPerJam) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.hargaPerJam = BigDecimal.valueOf(hargaPerJam);
    }
    
    public String getIdRuangan() {
        return idRuangan;
    }
    
    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }
    
    public String getNamaRuangan() {
        return namaRuangan;
    }
    
    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }
    
    public BigDecimal getHargaPerJam() {
        return hargaPerJam;
    }
    
    public void setHargaPerJam(BigDecimal hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }
    
    // Convenience setter
    public void setHargaPerJam(double hargaPerJam) {
        this.hargaPerJam = BigDecimal.valueOf(hargaPerJam);
    }
    
    @Override
    public String toString() {
        return idRuangan + " - " + namaRuangan + " (Rp " + hargaPerJam + "/jam)";
    }
}
