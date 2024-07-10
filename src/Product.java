//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

import java.io.Serializable;
//creating product superclass. implementing Serializable to accommodate save and load features
public abstract class Product implements Serializable {
    //encapsulating class data
    private final String productID;
    private final String productName;
    private final int availableAmount;
    private final double price;
    private int quantityBought=0;
    //creating class constructor
    public Product(String productID, String productName, int availableAmount, double price){
        this.productID = productID;
        this.productName = productName;
        this.availableAmount = availableAmount;
        this.price = price;
    }
    //creating method to get the price of products
    public double getPrice(){
        return this.price;
    }
    //creating method to get product ID of products
    public String getProductID(){
        return this.productID;
    }
    //creating method to get available number of products
    public int getAvailableAmount() {
        return this.availableAmount;
    }
    //creating method to get product name of products
    public String getProductName(){
        return this.productName;
    }
    //creating method to record multiple selections of the same product
    public void productBought(){
        quantityBought++;
    }
    //creating method to get selected products purchased quantity
    public int getQuantityBought(){
        return quantityBought;
    }
    //initiating methods to be used in sub/child class
    public String getInfo(){return "";}
    public String getType(){return "";}
    public String getProductDetails(){return "";}
    //creating new toString method
    @Override
    public String toString() {
        return "Product Id      : " + this.getProductID() + "\n" +
                "Product name    : " + this.getProductName() + "\n" +
                "Available stock : " + this.getAvailableAmount() + "\n" +
                "Unit price      : " + this.getPrice() + "\n";
    }
}
