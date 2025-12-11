/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

/**
 *
 * @author binta
 */
public class SPTransaksiDao {

    private Connection conn;

    public SPTransaksiDao(Connection connection) {
        this.conn = connection;
    }

    public static class SpResult {
        public String idTransaksi;
        public String tokenWifi;

        public SpResult(String idTransaksi, String tokenWifi) {
            this.idTransaksi = idTransaksi;
            this.tokenWifi = tokenWifi;
        }
    }

    public SpResult insertTransaksi(String idPelanggan, String idKasir, String idPembayaran) throws Exception {

        String sql = "{CALL sp_insert_transaksi_auto(?, ?, ?, ?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, idPelanggan);
            cs.setString(2, idKasir);
            cs.setString(3, idPembayaran);

            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);

            cs.execute();

            return new SpResult(
                cs.getString(4),
                cs.getString(5)
            );
        }
    }
}



