package model;

import classes.Sale;
import repository.SaleRepository;

import java.util.ArrayList;

public class SalesModel {
    SaleRepository repo;
    ArrayList<Sale> salesData;
    Object[][] salesDataTable;

    public SalesModel(){
        this.repo = new SaleRepository();
        updateProductData();
    }

    public void updateProductData(){
        int rows, columns = 5;
        salesData = this.repo.getSales();
        rows = salesData.size();

        salesDataTable = new Object[rows][columns];
        for(int i=0;i<rows;i++){
            salesDataTable[i][0] = salesData.get(i).getSaleid();
            salesDataTable[i][1] = salesData.get(i).getUserid();
            salesDataTable[i][2] = salesData.get(i).getProductid();
            salesDataTable[i][3] = salesData.get(i).getProduct_count();
            salesDataTable[i][4] = salesData.get(i).getCost();
        }
    }

    public SaleRepository getRepo() {
        return repo;
    }

    public void setRepo(SaleRepository repo) {
        this.repo = repo;
    }

    public ArrayList<Sale> getSalesData() {
        return salesData;
    }

    public void setSalesData(ArrayList<Sale> salesData) {
        this.salesData = salesData;
    }

    public Object[][] getSalesDataTable() {
        return salesDataTable;
    }

    public void setSalesDataTable(Object[][] salesDataTable) {
        this.salesDataTable = salesDataTable;
    }
}
