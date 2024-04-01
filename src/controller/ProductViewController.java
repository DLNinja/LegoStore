package controller;

import classes.Product;
import classes.Sale;
import classes.User;

import repository.ProductRepository;
import repository.SaleRepository;
import view.*;

import java.awt.*;
import java.util.ArrayList;

public class ProductViewController {
    ProductPageView view;
    ProductRepository productRepo;
    SaleRepository saleRepo;
    Product currentProduct;

    public ProductViewController(ProductPageView productPageView){
        this.view = productPageView;
        this.productRepo = new ProductRepository();
        this.saleRepo = new SaleRepository();
        this.currentProduct = this.view.getProduct();
    }

    public void changeBuyCount(Integer operation){
        int stock = this.view.getStock();
        int buyCount = this.view.getBuyCount();
        if(operation == 0 && buyCount > 0){
            this.view.setBuyCount((buyCount - 1));
        }else if(operation == 1 && buyCount < stock){
            this.view.setBuyCount((buyCount + 1));
        }
        this.view.updateDetails();
    }

    /// Buy Product - if enough stock, stock updates and new sale is registered
    public void buyProduct(Product product){
        int buyCount = this.view.getBuyCount();
        Product newProduct = product;
        if(buyCount > 0){
            int stock = Integer.parseInt(product.getStock());
            int newStock = stock - buyCount;
            if(newStock >= 0){
                newProduct.setStock(String.valueOf(newStock));
                String message = this.productRepo.insertProduct(newProduct);
                int saleId = this.saleRepo.getSaleIndex();
                int userId = this.view.getUser().getId();
                int productId = newProduct.getCode();
                int cost = Integer.parseInt(newProduct.getPrice()) * buyCount;
                Sale sale = new Sale(saleId, userId, productId, cost, buyCount);
                this.saleRepo.insertSale(sale);
                ArrayList<Sale> sales = this.saleRepo.getSales();
                this.view.setCurrentProduct(newProduct);
                this.view.setBuyCount(0);
                this.view.updateDetails();
            }
        }
    }

    public void goBack(){
        Point location = this.view.getLocation();
        User user = this.view.getUser();
        new StoreView(user, location);
        this.view.dispose();
    }
}
