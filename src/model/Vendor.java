/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author binta
 */
public class Vendor {
    private String idVendor;
    private String namaVendor;
    
    public Vendor() {}
    
    public Vendor(String idVendor, String namaVendor) {
        this.idVendor = idVendor;
        this.namaVendor = namaVendor;
    }
    
    public String getIdVendor() {
        return idVendor;
    }
    
    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }
    
    public String getNamaVendor() {
        return namaVendor;
    }
    
    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }
    
    @Override
    public String toString() {
        return idVendor + " - " + namaVendor;
    }
}
