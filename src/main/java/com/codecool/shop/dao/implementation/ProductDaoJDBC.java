package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    //    private transient List<Product> data = new ArrayList<>();
    private static JDBC instanceOFJDBC = JDBC.getInstance();
    private static ProductDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

//    private static void accept(PreparedStatement preparedStatement) {
//        try {
////            preparedStatement.setString(1, "id");
//            preparedStatement.setString(1, "name");
////            preparedStatement.setString(3, "currency");
//            preparedStatement.setString(2, "description");
////            preparedStatement.setString(5, "product_category_id");
////            preparedStatement.setString(6, "supplier_id");
//        } catch (SQLException e) {
//            throw new IllegalArgumentException("Wrong SQL parameter setup", e);
//        }
//
//    }

    @Override
    public void add(Product product) {

        String query = "INSERT INTO product " +
                "(name, description, price, currency, product_category_id, supplier_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Object[] productDetails = {
                product.getName(),
                product.getDescription(),
                product.getDefaultPrice(),
                product.getDefaultCurrency().getCurrencyCode(),
                product.getProductCategory().getId(),
                product.getSupplier().getId()
        };

        instanceOFJDBC.executeQuery(query, productDetails);
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        return instanceOFJDBC.securedExecuteQuery(Product.class, query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product " +
                "WHERE supplier_id = ?";
        return instanceOFJDBC.securedExecuteQuery(Product.class, query, (preparedStatement) -> {
            try {
                preparedStatement.setInt(1, supplier.getId());
            } catch (SQLException e) {
                throw new IllegalArgumentException("Wrong supplier_id", e);
            }
        });
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product " +
                "WHERE product_category_id = ?";
        return instanceOFJDBC.securedExecuteQuery(Product.class, query, (preparedStatement) -> {
            try {
                preparedStatement.setInt(1, productCategory.getId());
            } catch (SQLException e) {
                throw new IllegalArgumentException("Wrong product_category_id", e);
            }
        });
    }
}
