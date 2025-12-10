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

    public static class SpResult {
        public String idTransaksi;
        public String tokenWifi;

        public SpResult(String idTransaksi, String tokenWifi) {
            this.idTransaksi = idTransaksi;
            this.tokenWifi = tokenWifi;
        }
    }

    // ===========================
    //   CALL sp_insert_transaksi_auto
    // ===========================
    public SpResult insertTransaksi(String idPelanggan, String idKasir, String idPembayaran) throws Exception {

        String sql = "{CALL sp_insert_transaksi_auto(?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // IN parameter
            cs.setString(1, idPelanggan);
            cs.setString(2, idKasir);
            cs.setString(3, idPembayaran);

            // OUT parameter
            cs.registerOutParameter(4, Types.VARCHAR);  // o_id_transaksi
            cs.registerOutParameter(5, Types.VARCHAR);  // o_token_wifi

            cs.execute();

            String idTransaksi = cs.getString(4);
            String tokenWifi   = cs.getString(5);

            return new SpResult(idTransaksi, tokenWifi);
        }
    }
}



