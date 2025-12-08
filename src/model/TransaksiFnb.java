/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author binta
 */
public class TransaksiFnb {
    private String idTransaksi;
    private String idItem;
    private int kuantitas;
    
    //Relationship
    private FnB fnb;
    private Transaksi transaksi;
   
    public TransaksiFnb() {}
    
    public TransaksiFnb(String idTransaksi, String idItem, int kuantitas) {
        this.idTransaksi = idTransaksi;
        this.idItem = idItem;
        this.kuantitas = kuantitas;
    }
    
   
    public String getIdTransaksi() {
        return idTransaksi;
    }
    
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    
    public String getIdItem() {
        return idItem;
    }
    
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    
    public int getKuantitas() {
        return kuantitas;
    }
    
    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }
    
    public FnB getFnb() {
        return fnb;
    }
    
    public void setFnb(FnB fnb) {
        this.fnb = fnb;
    }
    
    public Transaksi getTransaksi() {
        return transaksi;
    }
    
    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }
    
    // Helper method to calculate subtotal
    public double getSubtotal() {
        if (fnb != null && fnb.getHarga() != null) {
            return fnb.getHarga().doubleValue() * kuantitas;
        }
        return 0.0;
    }
    
    @Override
    public String toString() {
        return "Transaksi: " + idTransaksi + 
               ", Item: " + idItem + 
               ", Qty: " + kuantitas;
    }
}
