/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.TransaksiFnb;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author binta
 */
public class TransaksiFnbDao {

    private Connection conn;

    // constructor default (tanpa UnitOfWork)
    public TransaksiFnbDao() {
        this.conn = DatabaseConnection.getConnection();
    }

    // constructor untuk UnitOfWork
    public TransaksiFnbDao(Connection conn) {
        this.conn = conn;
    }

    // INSERT
    public boolean insert(TransaksiFnb transaksiFnb) throws SQLException {
        String sql = "INSERT INTO tabel_transaksi_fnb (id_transaksi, id_item, kuantitas) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaksiFnb.getIdTransaksi());
            stmt.setString(2, transaksiFnb.getIdItem());
            stmt.setInt(3, transaksiFnb.getKuantitas());

            return stmt.executeUpdate() > 0;
        }
    }

    // GET BY TRANSAKSI
    public List<TransaksiFnb> getByTransaksi(String idTransaksi) throws SQLException {
        List<TransaksiFnb> list = new ArrayList<>();
        String sql = 
            "SELECT tf.*, f.nama_item, f.harga " +
            "FROM tabel_transaksi_fnb tf " +
            "JOIN tabel_fnb f ON tf.id_item = f.id_item " +
            "WHERE tf.id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idTransaksi);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransaksiFnb tf = new TransaksiFnb();
                tf.setIdTransaksi(rs.getString("id_transaksi"));
                tf.setIdItem(rs.getString("id_item"));
                tf.setKuantitas(rs.getInt("kuantitas"));
                list.add(tf);
            }

            rs.close();
        }

        return list;
    }

    // GET BY ITEM
    public List<TransaksiFnb> getByItem(String idItem) throws SQLException {
        List<TransaksiFnb> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi_fnb WHERE id_item = ? ORDER BY id_transaksi";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idItem);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransaksiFnb tf = new TransaksiFnb();
                tf.setIdTransaksi(rs.getString("id_transaksi"));
                tf.setIdItem(rs.getString("id_item"));
                tf.setKuantitas(rs.getInt("kuantitas"));
                list.add(tf);
            }

            rs.close();
        }

        return list;
    }

    // DELETE BY TRANSAKSI
    public boolean deleteByTransaksi(String idTransaksi) throws SQLException {
        String sql = "DELETE FROM tabel_transaksi_fnb WHERE id_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE BY TRANSAKSI + ITEM
    public boolean delete(String idTransaksi, String idItem) throws SQLException {
        String sql = "DELETE FROM tabel_transaksi_fnb WHERE id_transaksi = ? AND id_item = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTransaksi);
            stmt.setString(2, idItem);
            return stmt.executeUpdate() > 0;
        }
    }

    // TOTAL QUANTITY BY ITEM (bisa tetap pakai this.conn)
    public int getTotalQuantityByItem(String idItem, java.sql.Date startDate, java.sql.Date endDate) throws SQLException {

        String sql =
            "SELECT SUM(tf.kuantitas) AS total_qty " +
            "FROM tabel_transaksi_fnb tf " +
            "JOIN tabel_transaksi t ON tf.id_transaksi = t.id_transaksi " +
            "WHERE tf.id_item = ? " +
            "AND DATE(t.tanggal_transaksi) BETWEEN ? AND ?";

        int totalQty = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idItem);
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalQty = rs.getInt("total_qty");
            }

            rs.close();
        }

        return totalQty;
    }

    // POPULAR ITEMS
    public List<Object[]> getPopularItems(int limit) throws SQLException {
        List<Object[]> result = new ArrayList<>();

        String sql =
            "SELECT tf.id_item, f.nama_item, SUM(tf.kuantitas) AS total_terjual " +
            "FROM tabel_transaksi_fnb tf " +
            "JOIN tabel_fnb f ON tf.id_item = f.id_item " +
            "GROUP BY tf.id_item, f.nama_item " +
            "ORDER BY total_terjual DESC " +
            "LIMIT ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("id_item");
                row[1] = rs.getString("nama_item");
                row[2] = rs.getInt("total_terjual");
                result.add(row);
            }

            rs.close();
        }

        return result;
    }
}


