package classes;

public class Sale {
    private int saleid;
    private int userid;
    private int productid;
    private int product_count;
    private int cost;

    public Sale(int saleid, int userid, int productid, int cost, int product_count){
        this.saleid = saleid;
        this.userid = userid;
        this.productid = productid;
        this.cost = cost;
        this.product_count = product_count;
    }

    public int getSaleid() {
        return saleid;
    }

    public void setSaleId(int saleid) {
        this.saleid = saleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductId(int productid) {
        this.productid = productid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }
}
