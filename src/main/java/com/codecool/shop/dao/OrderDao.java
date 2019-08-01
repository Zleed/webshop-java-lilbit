package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    void add(Order order);

    void remove(int id);

    Order find(int id);

    Order getBy(int id);

    List<Order> getAll();
}
