package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.LinkedList;
import java.util.List;

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

        JDBC instanceOFJDBC = JDBC.getInstance();
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
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        return null;
    }

    @Override
    public void remove(int id) {
//        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
//        return data;
        return new LinkedList<>();
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return new LinkedList<>();
//        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return new LinkedList<>();
//        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
