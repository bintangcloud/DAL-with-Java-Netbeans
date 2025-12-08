/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author binta
 */
public class UnitOfWork {

    private Connection conn;

    public UnitOfWork() throws SQLException {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);  // MULAI TRANSAKSI
    }

    public Connection getConnection() {
        return conn;
    }

    public void commit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
    }
}

