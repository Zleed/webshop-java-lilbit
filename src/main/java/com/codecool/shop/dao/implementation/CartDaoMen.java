package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import java.util.HashMap;

public class CartDaoMen implements CartDao {

    private HashMap<Product, Integer> data = new HashMap<Product, Integer>();
    private static CartDaoMen instance = null;
    private float sumOfPrice = 0;

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
    public float getSumOfPrice () {
        return sumOfPrice;
    }

    @Override
    public void removeAll(Product product) {
        data.remove(product);
        sumOfPrice = 0;
    }

    @Override
    public void add(Product product) {
        int count = data.getOrDefault(product, 0);
        data.put(product, count + 1);
        sumOfPrice += product.getPrince();
    }

    @Override
    public void remove(Product product) {
        data.put(product, data.get(product) - 1);
        if (data.get(product) == 0) data.remove(product);
        sumOfPrice -= product.getPrince();
    }

    @Override
    public void find(Product product) {
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return data;
    }

    @Override
    public void setQuantity(Product product, int quantity) {
        if (quantity < 0) {
            for(int i=0;i<quantity*-1; i++) {
                remove(product);
                sumOfPrice -= product.getPrince();
            }
        }
        else if (quantity > 0) {
            for(int i=0; i<quantity; i++) {
                add(product);
                sumOfPrice += product.getPrince();
            }
        }
    }
}
