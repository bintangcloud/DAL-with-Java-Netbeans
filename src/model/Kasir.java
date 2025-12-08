/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author binta
 */
public class Kasir {
    private String idKasir;
    private String namaKasir;
    
    public Kasir() {}
    
    public Kasir(String idKasir, String namaKasir) {
        this.idKasir = idKasir;
        this.namaKasir = namaKasir;
    }
    
    public String getIdKasir() {
        return idKasir;
    }
    
    public void setIdKasir(String idKasir) {
        this.idKasir = idKasir;
    }
    
    public String getNamaKasir() {
        return namaKasir;
    }
    
    public void setNamaKasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }
    
    @Override
    public String toString() {
        return idKasir + " - " + namaKasir;
    }
}
