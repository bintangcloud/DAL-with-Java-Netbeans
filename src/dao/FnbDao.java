/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.FnB;
import util.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import view.Vendor;
/**
 *
 * @author binta
 */
public class FnbDao {

    public FnbDao() { 
    }
    
    // INSERT
    public boolean insert(FnB fnb) {
        String sql = "INSERT INTO tabel_fnb (id_item, nama_item, harga, id_vendor) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, fnb.getIdItem());
            stmt.setString(2, fnb.getNamaItem());
            stmt.setBigDecimal(3, fnb.getHarga());
            stmt.setString(4, fnb.getIdVendor());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting F&B: " + e.getMessage());
            return false;
        }
    }
    
    // GET ALL
    public List<FnB> getAll() {
        List<FnB> fnbList = new ArrayList<>();
        String sql = "SELECT f.*, v.nama_vendor FROM tabel_fnb f " +
                     "LEFT JOIN tabel_vendor v ON f.id_vendor = v.id_vendor " +
                     "ORDER BY f.id_item";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                FnB fnb = new FnB();
                fnb.setIdItem(rs.getString("id_item"));
                fnb.setNamaItem(rs.getString("nama_item"));
                fnb.setHarga(rs.getBigDecimal("harga"));
                fnb.setIdVendor(rs.getString("id_vendor"));
                
                // Set vendor name if needed
                // fnb.setVendorName(rs.getString("nama_vendor"));
                
                fnbList.add(fnb);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all F&B: " + e.getMessage());
        }
        
        return fnbList;
    }
    
    // GET BY ID
    public FnB getById(String idItem) {
        String sql = "SELECT f.*, v.nama_vendor FROM tabel_fnb f " +
                     "LEFT JOIN tabel_vendor v ON f.id_vendor = v.id_vendor " +
                     "WHERE f.id_item = ?";
        FnB fnb = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idItem);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                fnb = new FnB();
                fnb.setIdItem(rs.getString("id_item"));
                fnb.setNamaItem(rs.getString("nama_item"));
                fnb.setHarga(rs.getBigDecimal("harga"));
                fnb.setIdVendor(rs.getString("id_vendor"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting F&B by ID: " + e.getMessage());
        }
        
        return fnb;
    }
    
    // UPDATE
    public boolean update(FnB fnb) {
        String sql = "UPDATE tabel_fnb SET nama_item = ?, harga = ?, id_vendor = ? WHERE id_item = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, fnb.getNamaItem());
            stmt.setBigDecimal(2, fnb.getHarga());
            stmt.setString(3, fnb.getIdVendor());
            stmt.setString(4, fnb.getIdItem());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating F&B: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean delete(String idItem) {
        String sql = "DELETE FROM tabel_fnb WHERE id_item = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idItem);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting F&B: " + e.getMessage());
            return false;
        }
    }
    
    // GET BY VENDOR
    public List<FnB> getByVendor(String idVendor) {
        List<FnB> fnbList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_fnb WHERE id_vendor = ? ORDER BY nama_item";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idVendor);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                FnB fnb = new FnB();
                fnb.setIdItem(rs.getString("id_item"));
                fnb.setNamaItem(rs.getString("nama_item"));
                fnb.setHarga(rs.getBigDecimal("harga"));
                fnb.setIdVendor(rs.getString("id_vendor"));
                fnbList.add(fnb);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting F&B by vendor: " + e.getMessage());
        }
        
        return fnbList;
    }
    
    // SEARCH BY NAME
    public List<FnB> searchByName(String keyword) {
        List<FnB> fnbList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_fnb WHERE nama_item LIKE ? ORDER BY nama_item";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                FnB fnb = new FnB();
                fnb.setIdItem(rs.getString("id_item"));
                fnb.setNamaItem(rs.getString("nama_item"));
                fnb.setHarga(rs.getBigDecimal("harga"));
                fnb.setIdVendor(rs.getString("id_vendor"));
                fnbList.add(fnb);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching F&B: " + e.getMessage());
        }
        
        return fnbList;
    }
    
    // GET BY PRICE RANGE
    public List<FnB> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<FnB> fnbList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_fnb WHERE harga BETWEEN ? AND ? ORDER BY harga";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBigDecimal(1, minPrice);
            stmt.setBigDecimal(2, maxPrice);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                FnB fnb = new FnB();
                fnb.setIdItem(rs.getString("id_item"));
                fnb.setNamaItem(rs.getString("nama_item"));
                fnb.setHarga(rs.getBigDecimal("harga"));
                fnb.setIdVendor(rs.getString("id_vendor"));
                fnbList.add(fnb);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting F&B by price range: " + e.getMessage());
        }
        
        return fnbList;
    }

    public boolean update(Vendor vendorUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
