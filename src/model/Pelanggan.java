/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author binta
 */
public class Pelanggan {
    private String idPelanggan;
    private String namaPelanggan;
    private String noTelepon;
    
    public Pelanggan() {}
    
    public Pelanggan(String idPelanggan, String namaPelanggan, String noTelepon) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noTelepon = noTelepon;
    }
    
    public String getIdPelanggan() {
        return idPelanggan;
    }
    
    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    
    public String getNoTelepon() {
        return noTelepon;
    }
    
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
    @Override
    public String toString() {
        return idPelanggan + " - " + namaPelanggan + " (" + noTelepon + ")";
    }
}
