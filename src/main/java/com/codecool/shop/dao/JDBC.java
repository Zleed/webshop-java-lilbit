package com.codecool.shop.dao;

import com.codecool.shop.model.BaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public void CUDQuery(String query,
                         Consumer<PreparedStatement>... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Consumer<PreparedStatement> parameter : parameters) {
                parameter.accept(preparedStatement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void executeQuery(String query, Object... parameters) {
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            System.out.println("Creating statement...");
//            int i = 1;
//            for (Object parameter : parameters) {
//                preparedStatement.setObject(i++, parameter);
//            }
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public <T extends BaseModel> T find(Class<T> witness, String query, int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return BaseModel.createFrom(witness, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends BaseModel> List<T> getAll(Class<T> witness,
                                                String query,
                                                Consumer<PreparedStatement>... parameters) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            for (Consumer<PreparedStatement> parameter : parameters) {
                parameter.accept(preparedStatement);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<T> productList = new ArrayList<>();
                while (resultSet.next()) {
                    T product = BaseModel.createFrom(witness, resultSet);
                    productList.add(product);
                }
                return productList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
