package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMen;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/cart"})
public class APICartController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoJDBC.getInstance();
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
        
        writeToResponse(resp, stringify(cartData.getSumOfPrice()));

    }

    private void writeToResponse(HttpServletResponse resp, String strng) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(strng);
        out.flush();
    }

    private String stringify(Object objectToString) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(objectToString);
    }
}
