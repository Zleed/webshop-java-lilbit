package com.codecool.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseModel {

    private String name;
    private String email;
    private String hash;

    public User(ResultSet resultSet) throws SQLException {
        super(resultSet.getString("name"));
        this.email = resultSet.getString("email");
        this.hash = resultSet.getString("hash");
    }

    public User(String name, String email, String hash) {
        super(name);
        this.email = email;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHash() {
        return hash;
    }
}
