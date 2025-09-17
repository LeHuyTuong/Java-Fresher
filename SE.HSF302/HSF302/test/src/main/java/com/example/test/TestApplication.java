package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class TestApplication {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=mydb;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASS = "12345";

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        testConnection();
    }

    private static void testConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connection successful!");
            System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
        } catch (Exception e) {
            System.err.println("Connection failed! Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
