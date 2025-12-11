/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SPTransaksiDao;
import dao.TransaksiRuanganDao;
import dao.TransaksiFnbDao;
import dao.PelangganDao;
import util.UnitOfWork;
import model.TransaksiRuangan;
import model.TransaksiFnb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binta
 */
public class TransaksiService {
    
    public SPTransaksiDao.SpResult simpanTransaksiViaSP(
            String namaPelanggan,
            String noTelepon,
            String idKasir,
            String idPembayaran,
            List<TransaksiRuangan> listRuangan,
            List<TransaksiFnb> listFnb
    ) {

        UnitOfWork uow = null;

        try {
            // 1️⃣ Mulai UoW (auto-commit OFF)
            uow = new UnitOfWork();
            Connection conn = uow.getConnection();

            PelangganDao pelangganDao = new PelangganDao();

            // 2️⃣ Insert Pelanggan (pakai 1 koneksi)
            String idPelanggan = pelangganDao.insertPelangganAndGetId_UOW(
                    conn,
                    namaPelanggan,
                    noTelepon
            );

            // 3️⃣ Panggil SP Insert Transaksi
            SPTransaksiDao spDao = new SPTransaksiDao(conn);
            SPTransaksiDao.SpResult hasil = spDao.insertTransaksi(idPelanggan, idKasir, idPembayaran);

            if (hasil.idTransaksi == null || hasil.idTransaksi.isEmpty()) {
                throw new SQLException("ID Transaksi kosong dari SP!");
            }

            String idTransaksi = hasil.idTransaksi;

            // 4️⃣ Insert Ruangan
            TransaksiRuanganDao ruDao = new TransaksiRuanganDao(conn);

            if (listRuangan != null) {
                for (TransaksiRuangan r : listRuangan) {
                    r.setIdTransaksi(idTransaksi);
                    ruDao.insert(r);
                }
            }

            // 5️⃣ Insert FnB
            TransaksiFnbDao fnbDao = new TransaksiFnbDao(conn);

            if (listFnb != null) {
                for (TransaksiFnb f : listFnb) {
                    f.setIdTransaksi(idTransaksi);
                    fnbDao.insert(f);
                }
            }

            // 6️⃣ Commit semua
            uow.commit();

            return hasil;

        } catch (Exception e) {

            if (uow != null) {
                uow.rollback();
            }

            throw new RuntimeException("Transaksi gagal: " + e.getMessage(), e);

        } finally {
            if (uow != null) {
                uow.close();
            }
        }
    }
}


