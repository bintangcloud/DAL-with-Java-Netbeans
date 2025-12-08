package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author binta
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/database_dago"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "4125Des."; 

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Gagal koneksi ke database!");
            e.printStackTrace();
        }
        return null;
    }
}
