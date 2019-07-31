package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMen implements CartDao {

    private List<Product> data = new ArrayList<>();
    private static CartDaoMen instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CartDaoMen() {
    }

    public static CartDaoMen getInstance() {
        if (instance == null) {
            instance = new CartDaoMen();
        }
        return instance;
    }

    @Override
    public void removeAll(Product product) {
        data.removeIf(prod -> (prod.equals(product)));
    }

    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public void remove(Product product) {
        data.remove(product);
    }

    @Override
    public void find(Product product) {
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
