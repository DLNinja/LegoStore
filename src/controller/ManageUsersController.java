package controller;

import classes.User;
import model.ManageUsersModel;
import repository.UserRepository;
import view.AdminView;
import view.ManageUsersView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ManageUsersController {
    ManageUsersView view;
    ManageUsersModel model;
    UserRepository repo;

    public ManageUsersController(ManageUsersView view){
        this.view = view;
        this.model = new ManageUsersModel();
        this.repo = new UserRepository();
    }

    public Object[][] getUsersDataTable(){
        return this.model.getUsersDataTable();
    }

    public void createUser(){
        if(this.view.checkInputs()){
            this.view.showError("Fill all the fields");
        }else {
            String errorMessage = "Input error!";
            try {
                String username = this.view.getUsername();
                String password = this.view.getPassword();
                String status = this.view.getStatus();

                if (checkUsername(username) != -1) {
                    errorMessage = "Username already exists!";
                    throw new Exception();
                }
                int index = createAvailableIndex();
                User newUser = new User(index, username, password, status);
                String message = repo.insertUser(newUser);
                System.out.println("Created user " + username + " with index " + index);
                this.model.updateUsersData();
                this.view.updateUsersTable();
                //this.view.showMessage(message, 1);
            } catch (Exception e) {
                this.view.showError(errorMessage);
            }
        }
    }

    public void updateUser(){
        if(this.view.checkInputs()){
            this.view.showError("Fill all the fields");
        }else {
            String errorMessage = "Input error!";
            try {
                int index = this.view.getIndex();
                if(checkIndex(index)){
                    errorMessage = "Element at index "+index +" doesn't exist";
                    throw new Exception();
                }

                String username = this.view.getUsername();
                String password = this.view.getPassword();
                String status = this.view.getStatus();

                if(index == this.view.getUser().getId()){
                    if(Objects.equals(status, "User")){
                        errorMessage = "Can't change current manager to user!";
                        throw new Exception();
                    }
                }

                int checkIndex = checkUsername(username);
                if (checkIndex != -1 && checkIndex != index) {
                    errorMessage = "Username already exists!";
                    throw new Exception();
                }
                User newUser = new User(index, username, password, status);
                String message = repo.updateUser(newUser);
                System.out.println("Updated user with index " + index);
                this.model.updateUsersData();
                this.view.updateUsersTable();
                this.view.showError(message);
            } catch (Exception e) {
                this.view.showError(errorMessage);
            }
        }
    }

    public void deleteUser(){
            String errorMessage = "Index not selected!";
            try {
                int index = this.view.getIndex();
                if(index == this.view.getUser().getId()){
                    errorMessage = "Can't delete current active user";
                    throw new Exception();
                }
                if(checkIndex(index)){
                    errorMessage = "User doesn't exist!";
                    throw new Exception();
                }
                String username = this.view.getUsername();
                String password = this.view.getPassword();
                String status = this.view.getStatus();
                User newUser = new User(index, username, password, status);
                String message = repo.deleteUser(newUser);
                System.out.println("Deleted user with index " + index);
                this.model.updateUsersData();
                this.view.updateUsersTable();
                //this.view.showMessage(message, 1);
            } catch (Exception e) {
                this.view.showError(errorMessage);
            }
    }

    public int checkUsername(String newUsername){
        ArrayList<User> usersList = this.model.getUsersData();
        int noUsers = usersList.size();
        for(int i=0;i<noUsers;i++){
            String username = usersList.get(i).getUsername();
            if(Objects.equals(newUsername, username)){
                int index = usersList.get(i).getId();
                return index; // there already is a user with the same username, we return its index
            }
        }
        return -1;
    }

    public boolean checkIndex(int index){
        ArrayList<User> usersList = this.model.getUsersData();
        int noUsers = usersList.size();
        for(int i=0;i<noUsers;i++){
            int id = usersList.get(i).getId();
            if(id == index)
                return false;
        }
        return true;
    }

    public int createAvailableIndex(){
        int finalIndex = -1;
        ArrayList<User> usersList = this.model.getUsersData();
        int noUsers = usersList.size();
        for(int i=0;i<noUsers;i++){
            int index = usersList.get(i).getId();
            if(index > finalIndex) finalIndex = index;
        }
        return finalIndex + 1;
    }

    public void goBack(){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new AdminView(user, location);
        this.view.dispose();
    }

}
