/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Ruangan;
import util.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author binta
 */
public class RuanganDao {

    public RuanganDao(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // INSERT
    public boolean insert(Ruangan ruangan) {
        String sql = "INSERT INTO tabel_ruangan (id_ruangan, nama_ruangan, harga_per_jam) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ruangan.getIdRuangan());
            stmt.setString(2, ruangan.getNamaRuangan());
            stmt.setBigDecimal(3, ruangan.getHargaPerJam());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting ruangan: " + e.getMessage());
            return false;
        }
    }
    
    // GET ALL
    public List<Ruangan> getAll() {
        List<Ruangan> ruanganList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_ruangan ORDER BY id_ruangan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(rs.getString("id_ruangan"));
                ruangan.setNamaRuangan(rs.getString("nama_ruangan"));
                ruangan.setHargaPerJam(rs.getBigDecimal("harga_per_jam"));
                ruanganList.add(ruangan);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all ruangan: " + e.getMessage());
        }
        
        return ruanganList;
    }
    
    // GET BY ID
    public Ruangan getById(String idRuangan) {
        String sql = "SELECT * FROM tabel_ruangan WHERE id_ruangan = ?";
        Ruangan ruangan = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idRuangan);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ruangan = new Ruangan();
                ruangan.setIdRuangan(rs.getString("id_ruangan"));
                ruangan.setNamaRuangan(rs.getString("nama_ruangan"));
                ruangan.setHargaPerJam(rs.getBigDecimal("harga_per_jam"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting ruangan by ID: " + e.getMessage());
        }
        
        return ruangan;
    }
    
    // UPDATE
    public boolean update(Ruangan ruangan) {
        String sql = "UPDATE tabel_ruangan SET nama_ruangan = ?, harga_per_jam = ? WHERE id_ruangan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ruangan.getNamaRuangan());
            stmt.setBigDecimal(2, ruangan.getHargaPerJam());
            stmt.setString(3, ruangan.getIdRuangan());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating ruangan: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean delete(String idRuangan) {
        String sql = "DELETE FROM tabel_ruangan WHERE id_ruangan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idRuangan);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting ruangan: " + e.getMessage());
            return false;
        }
    }
    
    // GET BY PRICE RANGE
    public List<Ruangan> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Ruangan> ruanganList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_ruangan WHERE harga_per_jam BETWEEN ? AND ? ORDER BY harga_per_jam";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBigDecimal(1, minPrice);
            stmt.setBigDecimal(2, maxPrice);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(rs.getString("id_ruangan"));
                ruangan.setNamaRuangan(rs.getString("nama_ruangan"));
                ruangan.setHargaPerJam(rs.getBigDecimal("harga_per_jam"));
                ruanganList.add(ruangan);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting ruangan by price range: " + e.getMessage());
        }
        
        return ruanganList;
    }
    
    // GET AVAILABLE ROOMS (not booked at specific time)
    public List<Ruangan> getAvailableRooms(java.sql.Date tanggal, java.sql.Time waktuMulai, java.sql.Time waktuSelesai) {
        List<Ruangan> ruanganList = new ArrayList<>();
        String sql = """
            SELECT r.* FROM tabel_ruangan r
            WHERE r.id_ruangan NOT IN (
                SELECT tr.id_ruangan FROM tabel_transaksi_ruangan tr
                JOIN tabel_transaksi t ON tr.id_transaksi = t.id_transaksi
                WHERE DATE(t.tanggal_transaksi) = ?
                AND (
                    (? BETWEEN tr.waktu_mulai AND tr.waktu_selesai)
                    OR (? BETWEEN tr.waktu_mulai AND tr.waktu_selesai)
                    OR (tr.waktu_mulai BETWEEN ? AND ?)
                )
            )
            ORDER BY r.id_ruangan
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, tanggal);
            stmt.setTime(2, waktuMulai);
            stmt.setTime(3, waktuSelesai);
            stmt.setTime(4, waktuMulai);
            stmt.setTime(5, waktuSelesai);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(rs.getString("id_ruangan"));
                ruangan.setNamaRuangan(rs.getString("nama_ruangan"));
                ruangan.setHargaPerJam(rs.getBigDecimal("harga_per_jam"));
                ruanganList.add(ruangan);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting available rooms: " + e.getMessage());
        }
        
        return ruanganList;
    }
}
