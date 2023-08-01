package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ticketingSupport";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "nantha@1007";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

	
}