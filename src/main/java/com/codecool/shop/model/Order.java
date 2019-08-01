package com.codecool.shop.model;

import com.codecool.shop.dao.CartDao;

public class Order {

    private int id;

    private Status status;

    private CartDao orders;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrders(CartDao orders) {
        this.orders = orders;
    }
}

enum Status {
    NEW,
    CHECKED,
    PAID
}