package main;

public class Clothing extends Product{
    String size;
    String colour;

    public Clothing(String productID, String productName, String category, int quantity, double price, String size, String colour) {
        super(productID, productName, category, quantity, price);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Product ID: " + getProductID() +
                "\nCategory: Clothing" +
                "\nName: " + getProductName() +
                "\nSize: " + getSize() +
                "\nColor: " + getColour() +
                "\nPrice: " + getPrice() +
                "\nItem Available: " + getQuantity();
    }
}
