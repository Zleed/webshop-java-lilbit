package com.codecool.shop.model;

import com.google.gson.annotations.Expose;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;

    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name, String department, String description) {
        super(name,description);
        this.department = department;

    }


    public ProductCategory(int id, String name, String department, String description) {
        super(name,description);
        this.id = id;
        this.department = department;
    }

    public ProductCategory(ResultSet data) throws SQLException {
        super(data.getString("name"), data.getString("description"));
        this.id = data.getInt("id");
        this.setDepartment(data.getString("department"));
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}