package com.codecool.shop.dao;
//STEP 1. Import required packages

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class JDBC {

    //  Database URL & credentials
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String USER = "tom";
    private static final String PASS = "Zl9517532684Zl";

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

    public List<Product> executeQueryWithResult(String query, Object... parameters) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int i = 1;
            for (Object parameter : parameters) {
                preparedStatement.setObject(i++, parameter);
            }
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        getProductCategoryFromDB(resultSet.getInt("product_category_id")),
                        getSupplierFromDB(resultSet.getInt("supplier_id")));

                productList.add(product);
                System.out.println(product);
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private Supplier getSupplierFromDB(int supplierID) {
        System.out.println("Creating statement...");
        String query = "SELECT * FROM supplier WHERE id = "+  supplierID;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Supplier supplier = null;
            while (resultSet.next()) {
                supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            }
            return supplier;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private ProductCategory getProductCategoryFromDB(int productCategoryID) {
        System.out.println("Creating statement...");
        String query = "SELECT * FROM product_category WHERE id =" + productCategoryID;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ProductCategory productCategory = null;
            while (resultSet.next()) {
                productCategory = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"));
            }
            return productCategory;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public <T extends BaseModel> List<T> getAllProductCategory(Class<T> witness,
                                                               String query,
                                                               Consumer<PreparedStatement>... parameters) {
        System.out.println("Creating statement...");
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ) {

//            for (Consumer<PreparedStatement> parameter : parameters) {
//                parameter.accept(statement);
//            }


            try (ResultSet resultSet = statement.executeQuery(query)) {
                List<T> productList = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("ITT VAN");
                    System.out.println(resultSet.getString(2));
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
