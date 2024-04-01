package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import classes.User;

/// Contains the different operations for managing users data from the database
/// Operations: insert, read, update, delete, check username, login user, get available index

public class UserRepository {

    public String insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO user_cred (user_id, username, password, status) VALUES (?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);

            int index = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();
            String status = user.getStatus();

            preparedStatement.setInt(1, index);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, status);


            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + " Rows Affected. Success! Connection Closed!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String updateUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "UPDATE user_cred SET username = ?, password = ?, status = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(4, user.getId());
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getStatus());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + " Rows Affected. Success! Connection Closed!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String deleteUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "DELETE FROM user_cred WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + " Rows Affected. Success! Connection Closed!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM user_cred;";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int index = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String status = resultSet.getString("status");
                User user = new User(index, username, password, status);
                usersList.add(user);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usersList;
    }

    public boolean checkUsername(String new_username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM user_cred;";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                if(Objects.equals(username, new_username)) return false;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public User loginUser(String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM user_cred;";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String index = resultSet.getString("user_id");
                String name = resultSet.getString("username");
                String passcode = resultSet.getString("password");
                String status = resultSet.getString("status");
                if(Objects.equals(username, name)){
                    User newUser;
                    if(Objects.equals(password, passcode)){
                        newUser = new User(Integer.parseInt(index), name, passcode, status);
                    }else{
                        newUser = new User(Integer.parseInt(index), name, "", status);
                    }
                    return newUser;
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        return null;
    }

    public int getAvailableIndex(){
        int finalIndex = -1;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM user_cred;";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int index = resultSet.getInt("user_id");
                if(index > finalIndex)
                    finalIndex = index;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finalIndex + 1;
    }
}
