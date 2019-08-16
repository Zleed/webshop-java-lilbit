package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CartDaoMen implements CartDao {

    private HashMap<Product, Integer> data = new HashMap<>();
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
    public float getSumOfPrice() {
        return sumOfPrice;
    }


    @Override
    public void removeAll(Product product) {

        Iterator<Map.Entry<Product,Integer>> entryIt = data.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<Product,Integer> entry = entryIt.next();
            if (product.getId() == entry.getKey().getId()) {
                sumOfPrice -= product.getPrince() * getProductQuantity(entry.getKey());
                entryIt.remove();
            }
        }
    }

    @Override
    public void add(Product product) {
        boolean found = false;

        for (HashMap.Entry<Product, Integer> prod : data.entrySet()) {
            if (product.getId() == (prod.getKey().getId())) {
                data.put(prod.getKey(), prod.getValue() + 1);
                found = true;

            }
        }
        if(!found) data.put(product, 1);


        sumOfPrice += product.getPrince();
    }

    @Override
    public void remove(Product product) {

        Iterator<Map.Entry<Product,Integer>> entryIt = data.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<Product,Integer> entry = entryIt.next();
            if (product.getId() == entry.getKey().getId()) {
                data.put(entry.getKey(), entry.getValue() - 1);
                if (entry.getValue() == 0) entryIt.remove();
                sumOfPrice -= product.getPrince();

            }
        }
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return data;
    }

    @Override // TODO setQuantity refactor
    public void setQuantity(Product product, int quantity) {
        if (quantity < 0) {
            for (int i = 0; i < quantity * -1; i++) {
                remove(product);
            }
        } else if (quantity > 0) {
            for (int i = 0; i < quantity; i++) {
                add(product);
            }
        }
    }

    private int getProductQuantity(Product product) {
        for (HashMap.Entry<Product, Integer> prod : data.entrySet()) {
            if (product.equals(prod.getKey())) {
                return prod.getValue();
            }
        }
        return 0;
    }
}
