package controller;

import classes.Sale;
import classes.User;
import model.SalesModel;
import view.AdminView;
import view.SalesView;

import java.awt.*;

public class SalesController {
    SalesView view;
    SalesModel model;

    public SalesController(SalesView view){
        this.view = view;
        this.model = new SalesModel();
    }

    public Object[][] getSalesDataTable(){
        return this.model.getSalesDataTable();
    }

    public void goBack(){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new AdminView(user, location);
        this.view.dispose();
    }

}
