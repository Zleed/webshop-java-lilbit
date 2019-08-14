package com.codecool.shop.model;

import com.codecool.shop.dao.JDBC;
import com.google.gson.annotations.Expose;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;

public class Product extends BaseModel {

    @Expose
    private float defaultPrice;
    @Expose
    private Currency defaultCurrency;
    @Expose
    private ProductCategory productCategory;
    @Expose
    private Supplier supplier;

    public Product(ResultSet ProductData) throws SQLException {
        super(ProductData.getString("name"), ProductData.getString("description"));
        JDBC instanceOfJDBC = JDBC.getInstance();
        id = ProductData.getInt("id");
        setPrice(ProductData.getFloat("price"), ProductData.getString("currency"));
        setSupplier(instanceOfJDBC.find(Supplier.class,
                                  "SELECT * FROM supplier WHERE id = ?",
                                        ProductData.getInt("supplier_id")));
        setProductCategory(instanceOfJDBC.find(ProductCategory.class,
                                  "SELECT * FROM product_category WHERE id = ?",
                                        ProductData.getInt("product_category_id")));
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public float getPrince() {
        return defaultPrice;
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
