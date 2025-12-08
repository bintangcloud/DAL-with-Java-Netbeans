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
public class FnB {
     private String idItem;
    private String namaItem;
    private BigDecimal harga;
    private String idVendor;
    
    // Relationship
    private Vendor vendor;
    
    public FnB() {}
    
    public FnB(String idItem, String namaItem, BigDecimal harga, String idVendor) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.harga = harga;
        this.idVendor = idVendor;
    }
    
    // Overloaded constructor 
    public FnB(String idItem, String namaItem, double harga, String idVendor) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.harga = BigDecimal.valueOf(harga);
        this.idVendor = idVendor;
    }
    
    public String getIdItem() {
        return idItem;
    }
    
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    
    public String getNamaItem() {
        return namaItem;
    }
    
    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }
    
    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    // Convenience setter
    public void setHarga(double harga) {
        this.harga = BigDecimal.valueOf(harga);
    }
    
    public String getIdVendor() {
        return idVendor;
    }
    
    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }
    
    public Vendor getVendor() {
        return vendor;
    }
    
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    @Override
    public String toString() {
        return idItem + " - " + namaItem + " (Rp " + harga + ")";
    }
}
