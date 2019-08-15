package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static JDBC instanceOFJDBC = JDBC.getInstance();

    private static ProductCategoryDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }


    @Override
    public void add(ProductCategory category) {
                String query = "INSERT INTO product_category " +
                "(name, description, department) " +
                "VALUES (?, ?, ?)";

        instanceOFJDBC.CUDQuery(query, (preparedStatement -> {
            try {
                preparedStatement.setString(1, category.getName());
                preparedStatement.setString(2, category.getDescription());
                preparedStatement.setString(3, category.getDepartment());


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_category WHERE id = ?";
        return instanceOFJDBC.find(ProductCategory.class, query, id);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_category";
        return instanceOFJDBC.getAll(ProductCategory.class, query);
    }
}
