package main;

import classes.User;
import view.SignupView;
import view.StoreView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application has started!");
        Point startLocation = new Point(0, 0);

        try{
            new SignupView(startLocation); // start application from the sign-up window
            //new StoreView(new User(0, "admin", "", "Manager"), startLocation); // start application directly as a manager
            //new StoreView(new User(0, "user", "", "User"), startLocation); // start application directly as a user
        } catch(Exception e){
            System.out.println("Failed!");
            e.printStackTrace();
        }
    }
}
