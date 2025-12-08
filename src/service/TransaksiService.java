/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.TransaksiDao;
import dao.TransaksiRuanganDao;
import dao.TransaksiFnbDao;
import model.Transaksi;
import model.TransaksiRuangan;
import model.TransaksiFnb;
import util.DatabaseConnection;
import util.UnitOfWork;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author binta
 */
public class TransaksiService {
    
    private UnitOfWork uow;
    private TransaksiDao transaksiDao;
    private TransaksiRuanganDao transaksiRuanganDao;
    private TransaksiFnbDao transaksiFnbDao;

    public TransaksiService() throws SQLException {
        uow = new UnitOfWork();

        transaksiDao = new TransaksiDao(uow.getConnection());
        transaksiRuanganDao = new TransaksiRuanganDao(uow.getConnection());
        transaksiFnbDao = new TransaksiFnbDao(uow.getConnection());
    }

    
    public boolean simpanTransaksiLengkap(
            Transaksi transaksi,
            List<TransaksiRuangan> ruanganList,
            List<TransaksiFnb> fnbList
    ) {

        try {

            // 1️⃣ Simpan tabel_transaksi
            transaksiDao.insert(transaksi);

            // 2️⃣ Simpan ruangan (boleh kosong)
            if (ruanganList != null) {
                for (TransaksiRuangan tr : ruanganList) {
                    transaksiRuanganDao.insert(tr);
                }
            }

            // 3️⃣ Simpan F&B (boleh kosong)
            if (fnbList != null) {
                for (TransaksiFnb f : fnbList) {
                    transaksiFnbDao.insert(f);
                }
            }

            // 4️⃣ Kalau semua sukses → commit
            uow.commit();
            return true;

        } catch (Exception e) {

            System.err.println("❌ Error saat menyimpan transaksi lengkap:");
            System.err.println(e.getMessage());

            // rollback supaya data tidak setengah masuk
            try {
                uow.rollback();
                System.out.println("🔄 Rollback transaksi berhasil.");
            } catch (SQLException ex) {
                System.err.println("❌ Gagal rollback: " + ex.getMessage());
            }

            return false;

        }
    }
}
