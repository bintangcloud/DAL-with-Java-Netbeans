/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Vendor;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author binta
 */
public class VendorDao {

    public VendorDao() {
    }

    
    // INSERT
    public boolean insert(Vendor vendor) {
        String sql = "INSERT INTO tabel_vendor (id_vendor, nama_vendor) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vendor.getIdVendor());
            stmt.setString(2, vendor.getNamaVendor());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting vendor: " + e.getMessage());
            return false;
        }
    }
    
    // GET ALL
    public List<Vendor> getAll() {
        List<Vendor> vendorList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_vendor ORDER BY id_vendor";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Vendor vendor = new Vendor();
                vendor.setIdVendor(rs.getString("id_vendor"));
                vendor.setNamaVendor(rs.getString("nama_vendor"));
                vendorList.add(vendor);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all vendor: " + e.getMessage());
        }
        
        return vendorList;
    }
    
    // GET BY ID
    public Vendor getById(String idVendor) {
        String sql = "SELECT * FROM tabel_vendor WHERE id_vendor = ?";
        Vendor vendor = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idVendor);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                vendor = new Vendor();
                vendor.setIdVendor(rs.getString("id_vendor"));
                vendor.setNamaVendor(rs.getString("nama_vendor"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting vendor by ID: " + e.getMessage());
        }
        
        return vendor;
    }
    
    // UPDATE
    public boolean update(Vendor vendor) {
        String sql = "UPDATE tabel_vendor SET nama_vendor = ? WHERE id_vendor = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vendor.getNamaVendor());
            stmt.setString(2, vendor.getIdVendor());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating vendor: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean delete(String idVendor) {
        String sql = "DELETE FROM tabel_vendor WHERE id_vendor = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idVendor);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting vendor: " + e.getMessage());
            return false;
        }
    }
    
    // SEARCH BY NAME
    public List<Vendor> searchByName(String keyword) {
        List<Vendor> vendorList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_vendor WHERE nama_vendor LIKE ? ORDER BY nama_vendor";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Vendor vendor = new Vendor();
                vendor.setIdVendor(rs.getString("id_vendor"));
                vendor.setNamaVendor(rs.getString("nama_vendor"));
                vendorList.add(vendor);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching vendor: " + e.getMessage());
        }
        
        return vendorList;
    }
}
