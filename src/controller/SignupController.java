package controller;

import repository.UserRepository;
import view.LoginView;
import view.SignupView;
import classes.User;

import java.awt.*;

public class SignupController {
    UserRepository userRepository;
    SignupView userView;

    public SignupController(SignupView userView){
        this.userRepository =  new UserRepository();
        this.userView = userView;
    }

    public void addUser(){
        String errorMessage = "Input error!";
        try {
            String username = this.userView.getParam1();
            if(username.isEmpty()) {
                errorMessage = "Username field empty!";
                throw new Exception();
            }

            String password = this.userView.getParam2();
            if(password.isEmpty()) {
                errorMessage = "Password field empty!";
                throw new Exception();
            }

            if(!this.userRepository.checkUsername(username)){
                errorMessage = "Username already exists!";
                throw new Exception();
            }

            int index = this.userRepository.getAvailableIndex();
            User newUser = new User(index, username, password, "User");
            String message = userRepository.insertUser(newUser);
            //this.userView.showMessage(message,1);
            this.changeView();
        }
        catch (Exception e){
            this.userView.showMessage(errorMessage,0);
        }
    }

    public void changeView(){
        Point location = this.userView.getLocation();
        new LoginView(location);
        this.userView.dispose();
    }
}
