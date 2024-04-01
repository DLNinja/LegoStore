package model;

import classes.Product;
import repository.ProductRepository;

import java.util.ArrayList;

public class StoreModel {

    ProductRepository repo;
    ArrayList<Product> productsData;
    Object[][] productsDataTable;
    ArrayList<String> categories;

    public StoreModel(){
        this.repo = new ProductRepository();
        updateProductData();
        categories = this.repo.getProductCategories();
    }

    public void updateProductData(){
        int rows, columns = 7;
        productsData = this.repo.getProducts();
        rows = productsData.size();

        productsDataTable = new Object[rows][columns];
        for(int i=0;i<rows;i++){
            productsDataTable[i][0] = productsData.get(i).getCode();
            productsDataTable[i][1] = productsData.get(i).getName();
            productsDataTable[i][2] = productsData.get(i).getCategory();
            productsDataTable[i][3] = productsData.get(i).getPrice();
            productsDataTable[i][4] = productsData.get(i).getStock();
            productsDataTable[i][5] = productsData.get(i).getMinAge();
            productsDataTable[i][6] = productsData.get(i).getNoPieces();
        }
    }

    public ProductRepository getRepo() {
        return repo;
    }

    public void setRepo(ProductRepository repo) {
        this.repo = repo;
    }

    public ArrayList<Product> getProductsData() {
        return productsData;
    }

    public void setProductsData(ArrayList<Product> productsData) {
        this.productsData = productsData;
    }

    public Object[][] getProductsDataTable() {
        return productsDataTable;
    }

    public void setProductsDataTable(Object[][] productsDataTable) {
        this.productsDataTable = productsDataTable;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
}
