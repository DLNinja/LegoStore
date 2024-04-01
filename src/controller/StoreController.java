package controller;

import classes.Product;
import classes.User;

import model.StoreModel;
import repository.ProductRepository;
import view.*;

import java.awt.*;
import java.util.ArrayList;

public class StoreController {
    StoreView view;
    ProductRepository productRepository;
    StoreModel model;

    public StoreController(StoreView storeView){
        this.view = storeView;
        this.productRepository = new ProductRepository();
        this.model = new StoreModel();
    }

    public ArrayList<Product> getProducts(){
        return this.model.getProductsData();
    }

    public void logOut(){
        Point location = this.view.getLocation();
        new LoginView(location);
        this.view.dispose();
    }

    public void gotoAdminConsole(){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new AdminView(user, location);
        this.view.dispose();
    }

    public void gotoProductPage(Product product){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new ProductPageView(user, product, location);
        this.view.dispose();
    }
}
