package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/products/*"})
public class ProductController extends HttpServlet {

    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private ProductDao productDataStore = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productCategoryID = Integer.parseInt( req.getParameter("productCategoryID"));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Product> productsByID = productDataStore.getBy(productCategoryDataStore.find(productCategoryID));

        context.setVariable("products", productsByID);
        context.setVariable("category", productCategoryDataStore.find(productCategoryID));
        context.setVariable("suppliers",productsByID.stream().map( item -> item.getSupplier()).collect(Collectors.toSet()));
        engine.process("product/products.html", context, resp.getWriter());
    }
}
