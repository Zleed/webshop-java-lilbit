package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;
import java.sql.*;
public class ProductDaoJDBC implements ProductDao {

//    private transient List<Product> data = new ArrayList<>();
    private static JDBC data = JDBC.getInstance();
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

    @Override
    public void add(Product product) {
        ResultSet rs = data.executeQuery("SELECT name FROM product");
    }

    @Override
    public Product find(int id) {
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
//        data.remove(find(id));
    }

    @Override
    public List<Product> getAll()
    {
//        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
//        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
//        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
