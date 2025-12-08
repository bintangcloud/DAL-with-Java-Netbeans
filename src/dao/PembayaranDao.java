/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Pembayaran;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author binta
 */
public class PembayaranDao {

    public PembayaranDao(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    // INSERT
    public boolean insert(Pembayaran pembayaran) {
        String sql = "INSERT INTO tabel_pembayaran (id_pembayaran, jenis_pembayaran) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pembayaran.getIdPembayaran());
            stmt.setString(2, pembayaran.getJenisPembayaran());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting pembayaran: " + e.getMessage());
            return false;
        }
    }
    
    // GET ALL
    public List<Pembayaran> getAll() {
        List<Pembayaran> pembayaranList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_pembayaran ORDER BY id_pembayaran";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pembayaran pembayaran = new Pembayaran();
                pembayaran.setIdPembayaran(rs.getString("id_pembayaran"));
                pembayaran.setJenisPembayaran(rs.getString("jenis_pembayaran"));
                pembayaranList.add(pembayaran);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all pembayaran: " + e.getMessage());
        }
        
        return pembayaranList;
    }
    
    // GET BY ID
    public Pembayaran getById(String idPembayaran) {
        String sql = "SELECT * FROM tabel_pembayaran WHERE id_pembayaran = ?";
        Pembayaran pembayaran = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idPembayaran);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                pembayaran = new Pembayaran();
                pembayaran.setIdPembayaran(rs.getString("id_pembayaran"));
                pembayaran.setJenisPembayaran(rs.getString("jenis_pembayaran"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting pembayaran by ID: " + e.getMessage());
        }
        
        return pembayaran;
    }
    
    // UPDATE
    public boolean update(Pembayaran pembayaran) {
        String sql = "UPDATE tabel_pembayaran SET jenis_pembayaran = ? WHERE id_pembayaran = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pembayaran.getJenisPembayaran());
            stmt.setString(2, pembayaran.getIdPembayaran());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating pembayaran: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean delete(String idPembayaran) {
        String sql = "DELETE FROM tabel_pembayaran WHERE id_pembayaran = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idPembayaran);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting pembayaran: " + e.getMessage());
            return false;
        }
    }
}
