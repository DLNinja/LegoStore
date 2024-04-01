package repository;

import classes.Product;

import java.sql.*;
import java.util.ArrayList;

/// Contains the different operations for managing product data from the database
/// Operations: read, insert, update, delete, and get categories

public class ProductRepository {

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM product p JOIN category c on (p.category = c.cat_id)";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String name = resultSet.getString("name");
                String category = resultSet.getString("cat_name");
                String price = resultSet.getString("price");
                String stock = resultSet.getString("stock");
                String minAge = resultSet.getString("min_age");
                String noPieces = resultSet.getString("no_pieces");
                Product product = new Product(code, name, category, price, stock, minAge, noPieces);
                products.add(product);
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
        return products;
    }

    public String insertProduct(Product product){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO product (code, name, category, price, stock, min_age, no_pieces) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            int code = product.getCode();
            String name = product.getName();
            String category = product.getCategory();
            String price = product.getPrice();
            String stock = product.getStock();
            String min_age = product.getMinAge();
            String no_pieces = product.getNoPieces();

            preparedStatement.setInt(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, Integer.parseInt(category));
            preparedStatement.setInt(4, Integer.parseInt(price));
            preparedStatement.setInt(5, Integer.parseInt(stock));
            preparedStatement.setInt(6, Integer.parseInt(min_age));
            preparedStatement.setInt(7, Integer.parseInt(no_pieces));

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

    public String updateProduct(Product product){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "UPDATE product SET name = ?, category = ?, price = ?, stock = ?, min_age = ?, no_pieces = ? WHERE code = ?";
            preparedStatement = connection.prepareStatement(query);

            int code = product.getCode();
            String name = product.getName();
            String category = product.getCategory();
            String price = product.getPrice();
            String stock = product.getStock();
            String min_age = product.getMinAge();
            String no_pieces = product.getNoPieces();

            preparedStatement.setInt(7, code);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(category));
            preparedStatement.setInt(3, Integer.parseInt(price));
            preparedStatement.setInt(4, Integer.parseInt(stock));
            preparedStatement.setInt(5, Integer.parseInt(min_age));
            preparedStatement.setInt(6, Integer.parseInt(no_pieces));

            rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Updated product!");
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

    public String deleteProduct(int code){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "DELETE FROM product WHERE code = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, code);
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

    public ArrayList<String> getProductCategories(){
        ArrayList<String> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/legostore";
            String dbUsername = "postgres";
            String dbPassword = "databaseboss";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM category";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("cat_name");
                categories.add(category);
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
        return categories;
    }
}
