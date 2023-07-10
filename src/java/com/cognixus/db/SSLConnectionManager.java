package com.cognixus.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sinwei
 */
public class SSLConnectionManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/maindb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "itsapassword";
    private Connection connection;

    public SSLConnectionManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}
