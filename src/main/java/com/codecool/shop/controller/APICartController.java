package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMen;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart"})
public class APICartController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartData = CartDaoMen.getInstance();

        String process = req.getParameter("process");
        Product product = productDataStore.find(Integer.parseInt(req.getParameter("productID")));

        switch (process) {
            case "addToCart":
                cartData.add(product);
                break;
            case "removeFromCart":
                cartData.remove(product);
                break;
            case "removeAllFromCart":
                cartData.removeAll(product);
                break;
            case "setQuantity":
                cartData.setQuantity(product, Integer.parseInt(req.getParameter("quantity")));
        }
    }

}
