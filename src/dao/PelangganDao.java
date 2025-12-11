/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;
/**
 *
 * @author binta
 */
public class PelangganDao {

    public PelangganDao() {
    }
    // INSERT
    public boolean insert(Pelanggan pelanggan) {
        String sql = "INSERT INTO tabel_pelanggan (id_pelanggan, nama_pelanggan, no_telepon) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelanggan.getIdPelanggan());
            stmt.setString(2, pelanggan.getNamaPelanggan());
            stmt.setString(3, pelanggan.getNoTelepon());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting pelanggan: " + e.getMessage());
            return false;
        }
    }
    
    // GET ALL
    public List<Pelanggan> getAll() {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_pelanggan ORDER BY id_pelanggan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setIdPelanggan(rs.getString("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setNoTelepon(rs.getString("no_telepon"));
                pelangganList.add(pelanggan);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all pelanggan: " + e.getMessage());
        }
        
        return pelangganList;
    }
    
    // GET BY ID
    public Pelanggan getById(String idPelanggan) {
        String sql = "SELECT * FROM tabel_pelanggan WHERE id_pelanggan = ?";
        Pelanggan pelanggan = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idPelanggan);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setIdPelanggan(rs.getString("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setNoTelepon(rs.getString("no_telepon"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting pelanggan by ID: " + e.getMessage());
        }
        
        return pelanggan;
    }
    
    // UPDATE
    public boolean update(Pelanggan pelanggan) {
        String sql = "UPDATE tabel_pelanggan SET nama_pelanggan = ?, no_telepon = ? WHERE id_pelanggan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelanggan.getNamaPelanggan());
            stmt.setString(2, pelanggan.getNoTelepon());
            stmt.setString(3, pelanggan.getIdPelanggan());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating pelanggan: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean delete(String idPelanggan) {
        String sql = "DELETE FROM tabel_pelanggan WHERE id_pelanggan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idPelanggan);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting pelanggan: " + e.getMessage());
            return false;
        }
    }
    
    // SEARCH BY NAME
    public List<Pelanggan> searchByName(String keyword) {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_pelanggan WHERE nama_pelanggan LIKE ? ORDER BY nama_pelanggan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setIdPelanggan(rs.getString("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setNoTelepon(rs.getString("no_telepon"));
                pelangganList.add(pelanggan);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching pelanggan: " + e.getMessage());
        }
        
        return pelangganList;
    }
    
    // COUNT
    public int count() {
        String sql = "SELECT COUNT(*) as total FROM tabel_pelanggan";
        int total = 0;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("Error counting pelanggan: " + e.getMessage());
        }
        
        return total;
    }
    
    public String insertPelangganAndGetId_UOW(Connection conn, String nama, String telepon) throws SQLException {

    // 1. Ambil ID terakhir (pakai conn dari UOW)
    String sqlGet = "SELECT IFNULL(MAX(CAST(SUBSTRING(id_pelanggan,2) AS UNSIGNED)),0) AS lastId FROM tabel_pelanggan";
    PreparedStatement psGet = conn.prepareStatement(sqlGet);
    ResultSet rs = psGet.executeQuery();
    rs.next();
    int next = rs.getInt("lastId") + 1;

    // 2. Format ID baru
    String id = "P" + String.format("%02d", next);

    // 3. Insert pelanggan (pakai connection dari UOW → ikut rollback)
    String sqlInsert = "INSERT INTO tabel_pelanggan (id_pelanggan, nama_pelanggan, no_telepon) VALUES (?, ?, ?)";
    PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
    psInsert.setString(1, id);
    psInsert.setString(2, nama);
    psInsert.setString(3, telepon);
    psInsert.executeUpdate();

    return id;
}
}



