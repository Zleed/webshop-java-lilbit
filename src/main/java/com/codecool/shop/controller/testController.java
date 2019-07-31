package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMen;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/supplier"})
public class testController extends HttpServlet {

    private  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("process"));

        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile phone is a wireless handheld device that allows users to make and receive calls and to send text messages, among other features");
        Supplier google = new Supplier("Google", "Internet-related services and products");
        Product googlePixel = new Product("Google Pixel 3", 733.07f, "USD", "Even into 2019, the Google Pixel 3 has the best smartphone camera you can buy.", phone, google);

        List<Product> testList = new ArrayList<>();
        testList.add(googlePixel);

        String products_JSON = this.gson.toJson(testList);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(products_JSON);
        out.flush();

    }

}
