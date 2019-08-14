package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.sql.SQLException;

public class UserDaoJDBC implements UserDao {

    private static JDBC instanceOFJDBC = JDBC.getInstance();
    private static UserDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserDaoJDBC() {
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(User user) {

        String query = "INSERT INTO users " +
                "(name, email, hash) " +
                "VALUES (?, ?, ?)";

        instanceOFJDBC.CUDQuery(query, (preparedStatement -> {
            try {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getHash());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }
}
