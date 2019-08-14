package com.codecool.shop.model;

public class User {

    private String name;
    private String email;
    private String hash;

    public User(String name, String email, String hash) {
        this.name = name;
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
