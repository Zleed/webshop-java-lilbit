package com.codecool.shop.dao;
//STEP 1. Import required packages

import java.sql.*;

public class JDBC {

    //  Database URL & credentials
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String USER = "lorand";
    private static final String PASS = "lori123";

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


    public void executeQuery(String query, Object... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Creating statement...");
            int i = 1;
            for (Object parameter : parameters) {
                preparedStatement.setObject(i++, parameter);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQueryWithResult(String query, Object... parameters) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int i = 1;
            for (Object parameter : parameters) {
                preparedStatement.setObject(i++, parameter);
            }

            while (resultSet.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
