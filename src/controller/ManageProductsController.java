package controller;

import classes.Product;
import classes.User;
import model.ManageProductsModel;
import repository.ProductRepository;
import view.AdminView;
import view.ManageProductsView;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ManageProductsController {
    ManageProductsView view;
    ManageProductsModel model;
    ProductRepository repo;

    public ManageProductsController(ManageProductsView view){
        this.view = view;
        this.model = new ManageProductsModel();
        this.repo = new ProductRepository();
    }

    public void createProduct(){
        if(this.view.checkInputs()){
            this.view.showError("Fill all the fields");
        }else {
            String errorMessage = "Input error!";
            try {
                int code = this.view.getCode();
                if(!checkCode(code)){
                    errorMessage = "Code already in use!";
                    throw new Exception();
                }
                String name = this.view.getName();
                /// check the category and get index of the category
                String category = this.view.getCategory();
                ArrayList<String> categories = getProductCategories();
                int categoryIndex = 1;
                for(int i=0;i<categories.size();i++){
                    if(Objects.equals(categories.get(i), category)){
                        categoryIndex = i + 1;
                    }
                }
                String categoryS = String.valueOf(categoryIndex);
                int price = this.view.getPrice();
                if(price<=0){
                    errorMessage = "The price must be over 0!";
                    throw new Exception();
                }
                String priceS = String.valueOf(price);
                int stock = this.view.getStock();
                if(stock<0){
                    errorMessage = "Stock cannot be negatvie!";
                    throw new Exception();
                }
                String stockS = String.valueOf(stock);
                int min_age = this.view.getMinAge();
                if(min_age <=0 || min_age >= 100){
                    errorMessage = "Minimum age out of bounds!";
                    throw new Exception();
                }
                String min_ageS = String.valueOf(min_age);
                int no_pieces = this.view.getNoPieces();
                if(no_pieces <=0 ){
                    errorMessage = "Number of pieces is incorrect!";
                    throw new Exception();
                }
                String no_piecesS = String.valueOf(no_pieces);

                Product newProduct = new Product(code, name, categoryS, priceS, stockS, min_ageS, no_piecesS);
                String message = repo.insertProduct(newProduct);
                System.out.println("Created product " + name + " with code " + code);
                this.model.updateProductData();
                this.view.updateProductTable();
                //this.view.showMessage(message, 1);
            } catch (Exception e) {
                this.view.showError(errorMessage);
            }
        }
    }

    public void updateProduct(){
        if(this.view.checkInputs()){
            this.view.showError("Fill all the fields");
        }else {
            String errorMessage = "Input error!";
            try {
                int code = this.view.getCode();
                if(checkCode(code)){
                    errorMessage = "Product doesn't exist!";
                    throw new Exception();
                }
                String name = this.view.getName();
                /// check the category and get index of the category
                String category = this.view.getCategory();
                ArrayList<String> categories = getProductCategories();
                int categoryIndex = 1;
                for(int i=0;i<categories.size();i++){
                    if(Objects.equals(categories.get(i), category)){
                        categoryIndex = i + 1;
                    }
                }
                String categoryS = String.valueOf(categoryIndex);
                int price = this.view.getPrice();
                if(price<=0){
                    errorMessage = "The price must be over 0!";
                    throw new Exception();
                }
                String priceS = String.valueOf(price);
                int stock = this.view.getStock();
                if(stock<0){
                    errorMessage = "Stock cannot be negatvie!";
                    throw new Exception();
                }
                String stockS = String.valueOf(stock);
                int min_age = this.view.getMinAge();
                if(min_age <=0 || min_age >= 100){
                    errorMessage = "Minimum age out of bounds!";
                    throw new Exception();
                }
                String min_ageS = String.valueOf(min_age);
                int no_pieces = this.view.getNoPieces();
                if(no_pieces <=0 ){
                    errorMessage = "Number of pieces is incorrect!";
                    throw new Exception();
                }
                String no_piecesS = String.valueOf(no_pieces);

                Product newProduct = new Product(code, name, categoryS, priceS, stockS, min_ageS, no_piecesS);
                String message = repo.updateProduct(newProduct);
                System.out.println("Updated product with code " + code);
                this.model.updateProductData();
                this.view.updateProductTable();
                //this.view.showError(message);
            } catch (Exception e) {
                this.view.showError(errorMessage);
            }
        }
    }

    public void deleteProduct(){
        String errorMessage = "Input error!";
        try {
            int code = this.view.getCode();
            if(checkCode(code)){
                errorMessage = "Product with code "+ code + " doesn't exist";
                throw new Exception();
            }
            String message = repo.deleteProduct(code);
            System.out.println("Deleted product with code " + code);
            this.model.updateProductData();
            this.view.updateProductTable();
            this.view.showError(message);
        } catch (Exception e) {
            this.view.showError(errorMessage);
        }
    }

    public boolean checkCode(int code){
        ArrayList<Product> products = this.model.getProductsData();
        for(Product product: products){
            if(product.getCode() == code)
                return false;
        }
        return true;
    }

    public Object[][] getProductsDataTable(){
        return this.model.getProductsDataTable();
    }

    public ArrayList<String> getProductCategories(){
        return this.model.getCategories();
    }

    public void goBack(){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new AdminView(user, location);
        this.view.dispose();
    }

}
