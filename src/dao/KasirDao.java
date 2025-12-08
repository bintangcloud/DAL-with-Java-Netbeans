/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.DatabaseConnection;
import model.Kasir;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author binta
 */
public class KasirDao {

    public KasirDao(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
     // CREATE - Insert new kasir
    public boolean insert(Kasir kasir) {
        String sql = "INSERT INTO tabel_kasir (id_kasir, nama_kasir) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kasir.getIdKasir());
            stmt.setString(2, kasir.getNamaKasir());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting kasir: " + e.getMessage());
            return false;
        }
    }
    
    // READ ALL - Get all kasir
    public List<Kasir> getAll() {
        List<Kasir> kasirList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_kasir ORDER BY id_kasir";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setIdKasir(rs.getString("id_kasir"));
                kasir.setNamaKasir(rs.getString("nama_kasir"));
                kasirList.add(kasir);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all kasir: " + e.getMessage());
        }
        
        return kasirList;
    }
    
    // READ BY ID - Get kasir by ID
    public Kasir getById(String idKasir) {
        String sql = "SELECT * FROM tabel_kasir WHERE id_kasir = ?";
        Kasir kasir = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idKasir);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                kasir = new Kasir();
                kasir.setIdKasir(rs.getString("id_kasir"));
                kasir.setNamaKasir(rs.getString("nama_kasir"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting kasir by ID: " + e.getMessage());
        }
        
        return kasir;
    }
    
    // UPDATE - Update kasir
    public boolean update(Kasir kasir) {
        String sql = "UPDATE tabel_kasir SET nama_kasir = ? WHERE id_kasir = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kasir.getNamaKasir());
            stmt.setString(2, kasir.getIdKasir());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating kasir: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Delete kasir
    public boolean delete(String idKasir) {
        String sql = "DELETE FROM tabel_kasir WHERE id_kasir = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idKasir);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting kasir: " + e.getMessage());
            return false;
        }
    }
    
    // SEARCH - Search kasir by name
    public List<Kasir> searchByName(String keyword) {
        List<Kasir> kasirList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_kasir WHERE nama_kasir LIKE ? ORDER BY nama_kasir";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setIdKasir(rs.getString("id_kasir"));
                kasir.setNamaKasir(rs.getString("nama_kasir"));
                kasirList.add(kasir);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching kasir: " + e.getMessage());
        }
        
        return kasirList;
    }
    
    // COUNT - Count total kasir
    public int count() {
        String sql = "SELECT COUNT(*) as total FROM tabel_kasir";
        int total = 0;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("Error counting kasir: " + e.getMessage());
        }
        
        return total;
    }
    
    // CHECK IF EXISTS - Check if kasir ID exists
    public boolean exists(String idKasir) {
        String sql = "SELECT 1 FROM tabel_kasir WHERE id_kasir = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idKasir);
            ResultSet rs = stmt.executeQuery();
            
            boolean exists = rs.next();
            rs.close();
            
            return exists;
            
        } catch (SQLException e) {
            System.err.println("Error checking kasir existence: " + e.getMessage());
            return false;
        }
    }
}