package controller;

import classes.User;
import repository.UserRepository;
import view.*;

import java.awt.*;
import java.util.Objects;

public class LoginController {

    UserRepository userRepository;
    LoginView userView;

    public LoginController(LoginView userView){
        this.userRepository =  new UserRepository();
        this.userView = userView;
    }

    public void gotoApp(){
        String errorMessage = "Input error!";
        try {
            String username = this.userView.getParam1();
            if(username.isEmpty()) {
                errorMessage = "username field empty!";
                throw new Exception();
            }

            String password = this.userView.getParam2();
            if(password.isEmpty()) {
                errorMessage = "password field empty!";
                throw new Exception();
            }

            User user = this.userRepository.loginUser(username, password);
            if(user == null){
                errorMessage = "Account not registered!";
                throw new Exception();
            }else if(Objects.equals(user.getPassword(), "")){
                errorMessage = "Incorrect password!";
                throw new Exception();
            }
            this.gotoStore(user);
        }
        catch (Exception e){
            this.userView.showMessage(errorMessage,0);
        }
    }

    public void gotoSignup(){
        Point location = this.userView.getLocation();
        new SignupView(location);
        this.userView.dispose();
    }

    public void gotoStore(User newUser){
        Point location = this.userView.getLocation();
        new StoreView(newUser, location);
        this.userView.dispose();
    }
}
