/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

/**
 *
 * @author binta
 */
public class SPTransaksiDao {

    private Connection conn;

    public SPTransaksiDao(Connection conn) {
        this.conn = conn;
    }

    // memanggil stored procedure untuk insert transaksi otomatis
    public void insertTransaksiAuto(String idPelanggan, String idKasir, String idPembayaran) throws Exception {
        String sql = "{CALL sp_insert_transaksi_auto(?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);

        cs.setString(1, idPelanggan);
        cs.setString(2, idKasir);
        cs.setString(3, idPembayaran);

        cs.execute();
        cs.close();
    }
}


