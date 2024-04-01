package model;

import classes.User;
import repository.UserRepository;

import java.util.ArrayList;

public class ManageUsersModel {

    UserRepository repo;
    ArrayList<User> usersData;
    Object[][] usersDataTable;

    public ManageUsersModel(){
        this.repo = new UserRepository();
        updateUsersData();
    }

    public void updateUsersData(){
        int rows, columns = 4;
        usersData = this.repo.getUsers();
        rows = usersData.size();

        usersDataTable = new Object[rows][columns];
        for(int i=0;i<rows;i++){
            usersDataTable[i][0] = usersData.get(i).getId();
            usersDataTable[i][1] = usersData.get(i).getUsername();
            usersDataTable[i][2] = usersData.get(i).getPassword();
            usersDataTable[i][3] = usersData.get(i).getStatus();
        }
    }

    public UserRepository getRepo() {
        return repo;
    }

    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    public ArrayList<User> getUsersData() {
        return usersData;
    }

    public void setUsersData(ArrayList<User> usersData) {
        this.usersData = usersData;
    }

    public Object[][] getUsersDataTable() {
        return usersDataTable;
    }

    public void setUsersDataTable(Object[][] usersDataTable) {
        this.usersDataTable = usersDataTable;
    }
}
