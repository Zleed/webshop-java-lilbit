package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMen;
import com.codecool.shop.dao.implementation.ProductDaoMem;

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

        if (req.getParameter("process").equals("addToCart")) {
            cartData.add(productDataStore.find(Integer.parseInt(req.getParameter("productID"))));
        }
        else if (req.getParameter("process").equals("removeFromCart")) {
            cartData.remove(productDataStore.find(Integer.parseInt(req.getParameter("productID"))));
        }
        else {
            cartData.removeAll(productDataStore.find(Integer.parseInt(req.getParameter("productID"))));
        }

    }

}
