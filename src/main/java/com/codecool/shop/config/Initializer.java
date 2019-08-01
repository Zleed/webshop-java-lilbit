package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier google = new Supplier("Google", "Internet-related services and products");
        supplierDataStore.add(google);
        Supplier onePlus = new Supplier("OnePlus", "OnePlus, is a Chinese smartphone manufacturer based in Shenzhen, Guangdong.");
        supplierDataStore.add(onePlus);
        Supplier asus = new Supplier("Asus", "AsusTek Computer Inc. is a Taiwan-based multinational computer and phone hardware and electronics company");
        supplierDataStore.add(asus);
        Supplier dell = new Supplier("Dell", "Dell is a US multinational computer technology company that develops, sells, repairs, and supports computers and related products and services.");
        supplierDataStore.add(dell);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile phone is a wireless handheld device that allows users to make and receive calls and to send text messages, among other features");
        productCategoryDataStore.add(phone);
        ProductCategory noteBook = new ProductCategory("NoteBook", "Hardware", "Laptop computers, also known as notebooks, are portable computers that you can take with you and use in different environments.");
        productCategoryDataStore.add(noteBook);


        //setting up products and printing it
        // Tablets
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        //Phones
        productDataStore.add(new Product("Google Pixel 3", 733.07f, "USD", "Even into 2019, the Google Pixel 3 has the best smartphone camera you can buy.", phone, google));
        productDataStore.add(new Product("One Plus 7 Pro", 853.89f, "USD", "The One Plus 7 Pro delivers an exceptional Android experience by combining premium hardware and software.", phone, onePlus));
        productDataStore.add(new Product("Asus ROG Phone", 899.f, "USD", "It will no doubt be one of the most expensive gaming phones on the market.", phone, asus));
        productDataStore.add(new Product("Google Pixel 2", 233, "USD", "The Google Pixel 2 clues us in on why Google called its phone series Pixel: it was building the best camera on a phone.", phone, google));

        //Notebooks

        productDataStore.add(new Product("Dell XPS 15", 3151.29f, "USD", "Premium high-performance laptop with the latest 8th generation IntelCor i9, huge memory, 1TB SSD and a virtually borderless InfinityEdge UHD 4K display.", noteBook, dell));
    }
}
