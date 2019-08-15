package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoJDBC;

import java.util.HashMap;

public class Order {

    private int id;
    private int user_id;
    private Status status;
    private HashMap<Product,Integer> orderedProducts;



    public Order(Status status, HashMap<Product,Integer> orderedProducts) {
        this.status = status;
        this.user_id = 1;
        this.orderedProducts = orderedProducts;
    }


    public void saveToDataBase() {
        OrderDaoJDBC.getInstance().add(this);
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

    public int getUser_id() {
        return user_id;
    }
}