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
public class UnitOfWork implements AutoCloseable {
    private Connection conn;
    private boolean transactionActive = false;

    public UnitOfWork() throws SQLException {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);  // Mulai transaksi
        transactionActive = true;
    }

    public Connection getConnection() {
        return conn;
    }

    public void commit() throws SQLException {
        if (transactionActive) {
            conn.commit();
            conn.setAutoCommit(true);
            transactionActive = false;
        }
    }

    public void rollback() {
        try {
            if (transactionActive && conn != null && !conn.isClosed()) {
                conn.rollback();
                conn.setAutoCommit(true);
                transactionActive = false;
            }
        } catch (SQLException e) {
            System.err.println("Error saat rollback: " + e.getMessage());
        }
    }
    
    // Pastikan connection ditutup
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                if (transactionActive) {
                    rollback(); // Auto rollback jika belum commit
                }
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Helper method untuk try-with-resources
    public boolean isActive() {
        return transactionActive;
    }
}