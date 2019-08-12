package com.codecool.shop.dao;
//STEP 1. Import required packages
import java.sql.*;

public class JDBC {
    // JDBC driver name and database URL
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/codecoolshop";

    //  Database credentials
    private static final String USER = "username";
    private static final String PASS = "password";

    private static JDBC instance = null;

    private JDBC() {
    }


    public static JDBC getInstance() {
        if (instance == null) {
            instance = new JDBC();
        }
        return instance;
    }


    private Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public void executeQuery(String query, String... parameters) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (String parameter: parameters) {
                preparedStatement.setString(i++, parameter);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
