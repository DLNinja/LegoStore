package classes;

public class Product {
    private int code;
    private String name, category, price, stock, minAge, noPieces;

    public Product(int code, String name, String category, String price, String stock, String minAge, String noPieces){
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.minAge = minAge;
        this.noPieces = noPieces;
    }

    public void printProduct(){
        System.out.println(code + " " + name + " " + price + " " + stock + " " + minAge + " " + noPieces);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getNoPieces() {
        return noPieces;
    }

    public void setNoPieces(String noPieces) {
        this.noPieces = noPieces;
    }
}
