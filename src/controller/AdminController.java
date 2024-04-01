package controller;

import classes.User;
import view.*;

import java.awt.*;

public class AdminController {

    AdminView adminView;

    public AdminController(AdminView view){
        this.adminView = view;
    }

    public void gotoManageUsers(){
        Point location = this.adminView.getLocation();
        User user = this.adminView.getUser();
        new ManageUsersView(user, location);
        this.adminView.dispose();
    }

    public void gotoManageProducts(){
        Point location = this.adminView.getLocation();
        User user = this.adminView.getUser();
        new ManageProductsView(user, location);
        this.adminView.dispose();
    }

    public void gotoSales(){
        Point location = this.adminView.getLocation();
        User user = this.adminView.getUser();
        new SalesView(user, location);
        this.adminView.dispose();
    }

    public void goBack(){
        Point location = this.adminView.getLocation();
        User user = this.adminView.getUser();
        new StoreView(user, location);
        this.adminView.dispose();
    }

    public void logOut(){
        Point location = this.adminView.getLocation();
        new LoginView(location);
        this.adminView.dispose();
    }

}
