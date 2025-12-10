/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.TransaksiRuangan;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author binta
 */
public class TransaksiRuanganDao {

    private Connection conn;

    // constructor default (non-UoW)
    public TransaksiRuanganDao() {
    }

    // constructor untuk UnitOfWork
    public TransaksiRuanganDao(Connection conn) {
        this.conn = conn;
    }

    // INSERT
    public boolean insert(TransaksiRuangan tr) throws SQLException {
        String sql = "INSERT INTO tabel_transaksi_ruangan (id_transaksi, id_ruangan, waktu_mulai, waktu_selesai) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tr.getIdTransaksi());
            stmt.setString(2, tr.getIdRuangan());
            stmt.setTime(3, tr.getWaktuMulai());
            stmt.setTime(4, tr.getWaktuSelesai());

            return stmt.executeUpdate() > 0;
        }
    }

    // GET ALL
    public List<TransaksiRuangan> getAll() throws SQLException {
        List<TransaksiRuangan> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi_ruangan";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TransaksiRuangan tr = new TransaksiRuangan();
                tr.setIdTransaksi(rs.getString("id_transaksi"));
                tr.setIdRuangan(rs.getString("id_ruangan"));
                tr.setWaktuMulai(rs.getTime("waktu_mulai"));
                tr.setWaktuSelesai(rs.getTime("waktu_selesai"));
                list.add(tr);
            }
        }

        return list;
    }

    // GET BY ID TRANSAKSI
    public List<TransaksiRuangan> getByIdTransaksi(String idTransaksi) throws SQLException {
        List<TransaksiRuangan> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi_ruangan WHERE id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idTransaksi);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransaksiRuangan tr = new TransaksiRuangan();
                tr.setIdTransaksi(rs.getString("id_transaksi"));
                tr.setIdRuangan(rs.getString("id_ruangan"));
                tr.setWaktuMulai(rs.getTime("waktu_mulai"));
                tr.setWaktuSelesai(rs.getTime("waktu_selesai"));
                list.add(tr);
            }

            rs.close();
        }

        return list;
    }

    // DELETE BY ID TRANSAKSI
    public boolean deleteByIdTransaksi(String idTransaksi) throws SQLException {
        String sql = "DELETE FROM tabel_transaksi_ruangan WHERE id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // INSERT versi sederhana (tanpa membuat object model)
public void insert(String idTransaksi, String idRuangan, Time waktuMulai, Time waktuSelesai) throws SQLException {

    String sql = "INSERT INTO tabel_transaksi_ruangan (id_transaksi, id_ruangan, waktu_mulai, waktu_selesai) "
               + "VALUES (?, ?, ?, ?)";

    try (Connection c = (conn != null ? conn : DatabaseConnection.getConnection());
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, idTransaksi);
        ps.setString(2, idRuangan);
        ps.setTime(3, waktuMulai);
        ps.setTime(4, waktuSelesai);

        ps.executeUpdate();
    }
}

}
