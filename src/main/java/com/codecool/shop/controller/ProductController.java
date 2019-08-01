package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/products/*"})
public class ProductController extends HttpServlet {

    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

    private ProductDao productDataStore = ProductDaoMem.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        WebContext context = new WebContext(req, resp, req.getServletContext());

        setServletContext(context, req);

        engine.process("product/products.html", context, resp.getWriter());
    }

    private void setServletContext(WebContext context, HttpServletRequest req) {

        int productCategoryID = Integer.parseInt(req.getParameter("productCategoryID"));

        List<Product> productsByID = productDataStore.getBy(productCategoryDataStore.find(productCategoryID));

        context.setVariable("products", productsByID);
        context.setVariable("category", productCategoryDataStore.find(productCategoryID));
        context.setVariable("suppliers", productsByID
                .stream()
                .map(item -> item.getSupplier())
                .collect(Collectors.toSet()));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productCategoryID = Integer.parseInt(req.getParameter("productCategoryID"));

        int supplierID = Integer.parseInt(req.getParameter("supplierID"));

        List<Product> products = getProductsByCategoryID(productCategoryID);

        List<Product> filteredProducts = filterProductsBySupplierId(products, supplierID);

        String productsJSON = stringify(filteredProducts);

        writeToResponse(resp, productsJSON);

    }

    private void writeToResponse(HttpServletResponse resp, String strng) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(strng);
        out.flush();
    }

    private List<Product> getProductsByCategoryID(int productCategoryID) {

        return productDataStore.getBy(productCategoryDataStore.find(productCategoryID));
    }

    private List<Product> filterProductsBySupplierId(List<Product> products, int supplierID) {
        return products
                .stream()
                .filter(item -> item.getSupplier().getId() == supplierID)
                .collect(Collectors.toList());
    }

    private String stringify(Object objectToString) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(objectToString);
    }


}
