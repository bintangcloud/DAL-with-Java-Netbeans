/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Transaksi;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author binta
 */
public class TransaksiDao {

    private Connection conn;

    // constructor default (tanpa UnitOfWork)
    public TransaksiDao() {
        this.conn = DatabaseConnection.getConnection();
    }

    // constructor untuk UnitOfWork (pakai koneksi transaksi)
    public TransaksiDao(Connection conn) {
        this.conn = conn;
    }

    // INSERT
    public boolean insert(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO tabel_transaksi " +
                     "(id_transaksi, tanggal_transaksi, id_pelanggan, id_kasir, id_pembayaran, token_wifi) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaksi.getIdTransaksi());
            stmt.setTimestamp(2, new Timestamp(transaksi.getTanggalTransaksi().getTime()));
            stmt.setString(3, transaksi.getIdPelanggan());
            stmt.setString(4, transaksi.getIdKasir());
            stmt.setString(5, transaksi.getIdPembayaran());
            stmt.setString(6, transaksi.getTokenWifi());

            return stmt.executeUpdate() > 0;
        }
    }

    // GET ALL
    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi ORDER BY tanggal_transaksi DESC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getString("id_transaksi"));
                transaksi.setTanggalTransaksi(rs.getTimestamp("tanggal_transaksi"));
                transaksi.setIdPelanggan(rs.getString("id_pelanggan"));
                transaksi.setIdKasir(rs.getString("id_kasir"));
                transaksi.setIdPembayaran(rs.getString("id_pembayaran"));
                transaksi.setTokenWifi(rs.getString("token_wifi"));
                transaksiList.add(transaksi);
            }
        }

        return transaksiList;
    }

    // GET BY ID
    public Transaksi getById(String idTransaksi) throws SQLException {
        String sql = "SELECT * FROM tabel_transaksi WHERE id_transaksi = ?";
        Transaksi transaksi = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idTransaksi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getString("id_transaksi"));
                transaksi.setTanggalTransaksi(rs.getTimestamp("tanggal_transaksi"));
                transaksi.setIdPelanggan(rs.getString("id_pelanggan"));
                transaksi.setIdKasir(rs.getString("id_kasir"));
                transaksi.setIdPembayaran(rs.getString("id_pembayaran"));
                transaksi.setTokenWifi(rs.getString("token_wifi"));
            }

            rs.close();
        }

        return transaksi;
    }

    // GET LAST ID
    public String getLastId() throws SQLException {
        String sql = "SELECT id_transaksi FROM tabel_transaksi ORDER BY id_transaksi DESC LIMIT 1";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getString("id_transaksi");
            }
        }

        return null;
    }

    // GET BY DATE RANGE
    public List<Transaksi> getByDateRange(Date startDate, Date endDate) throws SQLException {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi WHERE tanggal_transaksi BETWEEN ? AND ? ORDER BY tanggal_transaksi";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getString("id_transaksi"));
                transaksi.setTanggalTransaksi(rs.getTimestamp("tanggal_transaksi"));
                transaksi.setIdPelanggan(rs.getString("id_pelanggan"));
                transaksi.setIdKasir(rs.getString("id_kasir"));
                transaksi.setIdPembayaran(rs.getString("id_pembayaran"));
                transaksi.setTokenWifi(rs.getString("token_wifi"));
                transaksiList.add(transaksi);
            }

            rs.close();
        }

        return transaksiList;
    }

    // GET BY PELANGGAN
    public List<Transaksi> getByPelanggan(String idPelanggan) throws SQLException {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi WHERE id_pelanggan = ? ORDER BY tanggal_transaksi DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPelanggan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getString("id_transaksi"));
                transaksi.setTanggalTransaksi(rs.getTimestamp("tanggal_transaksi"));
                transaksi.setIdPelanggan(rs.getString("id_pelanggan"));
                transaksi.setIdKasir(rs.getString("id_kasir"));
                transaksi.setIdPembayaran(rs.getString("id_pembayaran"));
                transaksi.setTokenWifi(rs.getString("token_wifi"));
                transaksiList.add(transaksi);
            }

            rs.close();
        }

        return transaksiList;
    }

    // GET BY KASIR
    public List<Transaksi> getByKasir(String idKasir) throws SQLException {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi WHERE id_kasir = ? ORDER BY tanggal_transaksi DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idKasir);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getString("id_transaksi"));
                transaksi.setTanggalTransaksi(rs.getTimestamp("tanggal_transaksi"));
                transaksi.setIdPelanggan(rs.getString("id_pelanggan"));
                transaksi.setIdKasir(rs.getString("id_kasir"));
                transaksi.setIdPembayaran(rs.getString("id_pembayaran"));
                transaksi.setTokenWifi(rs.getString("token_wifi"));
                transaksiList.add(transaksi);
            }

            rs.close();
        }

        return transaksiList;
    }

    // UPDATE
    public boolean update(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE tabel_transaksi SET " +
                     "tanggal_transaksi = ?, id_pelanggan = ?, id_kasir = ?, id_pembayaran = ?, token_wifi = ? " +
                     "WHERE id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(transaksi.getTanggalTransaksi().getTime()));
            stmt.setString(2, transaksi.getIdPelanggan());
            stmt.setString(3, transaksi.getIdKasir());
            stmt.setString(4, transaksi.getIdPembayaran());
            stmt.setString(5, transaksi.getTokenWifi());
            stmt.setString(6, transaksi.getIdTransaksi());

            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(String idTransaksi) throws SQLException {
        String sql = "DELETE FROM tabel_transaksi WHERE id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            return stmt.executeUpdate() > 0;
        }
    }

}
