package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJDBCTest {
    ProductDao productDataStore = ProductDaoJDBC.getInstance();

    private Supplier apple = new Supplier("Apple", "Dell is a US multinational computer technology " +
            "company that develops, sells, repairs, and supports computers and related products and services.");
    private ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile " +
            "phone is a wireless handheld device that allows users to make and receive calls and to send text messages, " +
            "among other features");
    private Product product = new Product("iPhone", 500, "USD", "nice", phone, apple);

    private Supplier google = new Supplier("Google", "Internet-related services and products");

    @Test
    void isNewProductAdded() {
        productDataStore = ProductDaoJDBC.getInstance();
        productDataStore.add(product);
        assertNotNull(productDataStore.find(product.getId()));
    }

    @Test
    void findAnItemWithExistingId() {
        assertNotNull(productDataStore.find(27));
    }

    @Test
    void findAnItemWithNonExistingId() {
        try {
            assertNotNull(productDataStore.find(99));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No product found");// element not found
        }
    }

    @Test
    void removeAnExistingItem() {
        productDataStore.remove(26);
        try {
            assertNull(productDataStore.find(26));
        } catch (NoSuchElementException e) {
            // Successfully removed
        }
    }
}


