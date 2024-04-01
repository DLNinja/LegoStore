package repository;

import classes.Sale;

import java.sql.*;
import java.util.ArrayList;

/// Contains the different operations for managing sales data from the database
/// Operations: read, insert

public class SaleRepository {

    public ArrayList<Sale> getSales(){
        ArrayList<Sale> sales = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM sales";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int saleId = resultSet.getInt("saleid");
                int userId = resultSet.getInt("userid");
                int productId = resultSet.getInt("productid");
                int cost = resultSet.getInt("cost");
                int product_count = resultSet.getInt("product_count");
                Sale sale = new Sale(saleId, userId, productId, cost, product_count);
                sales.add(sale);
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
        return sales;
    }

    public String insertSale(Sale newSale){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO sales (saleid, userid, productid, cost, product_count) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            int saleId = newSale.getSaleid();
            int userId = newSale.getUserid();
            int productId = newSale.getProductid();
            int cost = newSale.getCost();
            int product_count = newSale.getProduct_count();

            preparedStatement.setInt(1, saleId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, productId);
            preparedStatement.setInt(4, cost);
            preparedStatement.setInt(5, product_count);

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

    public int getSaleIndex(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = -1;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM sales";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            index = 1;
            while (resultSet.next()) {
                index++;
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
        return index;
    }
}
