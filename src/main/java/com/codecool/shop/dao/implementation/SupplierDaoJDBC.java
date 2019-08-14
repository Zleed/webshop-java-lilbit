package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

    private static JDBC instanceOFJDBC = JDBC.getInstance();
    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {

    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE id = ?";
        return instanceOFJDBC.find(Supplier.class, query, id);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier";
        return instanceOFJDBC.getAll(Supplier.class, query);
    }
}
