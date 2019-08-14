package com.codecool.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {

    private List<Product> products = new ArrayList<>();


    public Supplier(ResultSet supplierData) throws SQLException {
        super(supplierData.getString("name"));
        id = supplierData.getInt("id");
        description = supplierData.getString("description");
    }


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}