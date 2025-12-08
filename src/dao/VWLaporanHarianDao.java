/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author binta
 */
public class VWLaporanHarianDao {

    private Connection conn;

    public VWLaporanHarianDao(Connection conn) {
        this.conn = conn;
    }

    // mengambil data laporan harian
    public List<String[]> getLaporanHarian() throws Exception {
        String sql = "SELECT * FROM view_laporan_harian ORDER BY tanggal DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<String[]> data = new ArrayList<>();

        while (rs.next()) {
            data.add(new String[]{
                rs.getString("tanggal"),
                rs.getString("total_ruangan"),
                rs.getString("total_fnb"),
                rs.getString("total_semua")
            });
        }

        rs.close();
        ps.close();
        return data;
    }
}

