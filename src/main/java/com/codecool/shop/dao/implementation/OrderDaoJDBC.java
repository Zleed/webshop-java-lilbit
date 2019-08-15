package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDaoJDBC implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoJDBC instance = null;
    private static JDBC instanceOFJDBC = JDBC.getInstance();

    private OrderDaoJDBC() {
    }

    public static OrderDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
                String query = "INSERT INTO product " +
                "(user_id, product_id, quantity, status) " +
                "VALUES (?, ?, ?, ?)";
                for (HashMap.Entry<Product, Integer> prod : order.getOrderedProducts().entrySet()) {
                    instanceOFJDBC.CUDQuery(query, (preparedStatement -> {
                        try {
                            preparedStatement.setInt(1, order.getUser_id());
                            preparedStatement.setInt(2, prod.getKey().getId());
                            preparedStatement.setInt(3, prod.getValue());
                            preparedStatement.setString(4, order.getStatus().toString());
                            preparedStatement.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }));
                }


    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public Order getBy(int id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return data;
    }
}
