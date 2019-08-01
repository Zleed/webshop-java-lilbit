package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public interface CartDao {

    void add(Product product);
    void remove(Product product);
    void find(Product product);
    void removeAll(Product product);
    HashMap<Product, Integer> getAll();

    void setQuantity(Product product, int quantity);
}
