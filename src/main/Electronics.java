package main;

public class Electronics extends Product{
    String brand;
    int warrantyPeriod;

    public Electronics(String productID, String productName, String category, int quantity, double price, String brand, int warrantyPeriod) {
        super(productID, productName, category, quantity, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Product ID: " + getProductID() +
                "\nCategory: Electronics" +
                "\nName: " + getProductName() +
                "\nBrand: " + getBrand()+
                "\nWarranty: "+getWarrantyPeriod()+" months"+
                "\nPrice: "+getPrice()+
                "\nItem Available : "+getQuantity();
    }
}
