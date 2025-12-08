/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author binta
 */
public class Pembayaran {
    private String idPembayaran;
    private String jenisPembayaran;
    
    public Pembayaran() {}
    
    public Pembayaran(String idPembayaran, String jenisPembayaran) {
        this.idPembayaran = idPembayaran;
        this.jenisPembayaran = jenisPembayaran;
    }
    
    public String getIdPembayaran() {
        return idPembayaran;
    }
    
    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }
    
    public String getJenisPembayaran() {
        return jenisPembayaran;
    }
    
    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }
    
    @Override
    public String toString() {
        return idPembayaran + " - " + jenisPembayaran;
    }
}
