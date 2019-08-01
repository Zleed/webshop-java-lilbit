package com.codecool.shop.model;

import java.util.HashMap;

public class Order {

    private int id;

    private Status status;

    private HashMap<Product,Integer> orderedProducts;

    public Order(Status status, HashMap<Product,Integer> orderedProducts) {
        this.status = status;
        this.orderedProducts = orderedProducts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrderedProducts(HashMap<Product, Integer> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public HashMap<Product, Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}