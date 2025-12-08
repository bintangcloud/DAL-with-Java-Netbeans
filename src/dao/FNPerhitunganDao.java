/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author binta
 */
public class FNPerhitunganDao {


    private Connection conn;

    public FNPerhitunganDao(Connection conn) {
        this.conn = conn;
    }

    // memanggil function total belanja
    public double hitungTotalBelanja(String idTransaksi) throws Exception {
        String sql = "SELECT fn_total_belanja(?) AS total";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idTransaksi);

        ResultSet rs = ps.executeQuery();

        double total = 0;
        if (rs.next()) {
            total = rs.getDouble("total");
        }

        rs.close();
        ps.close();
        return total;
    }
}

