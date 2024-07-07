package main;

import utils.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    Scanner input = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. Delete Product");
        System.out.println("3. Print Product");
        System.out.println("4. Save Products to File");
        System.out.println("5. Load Products from File");
        System.out.println("6. Back to the Main Menu");
        System.out.println("What is your choice ?");
        String choice = input.next();
        choiceActivator(choice);
    }

    private void choiceActivator(String choice){
        switch (choice){
            case "1":
                getProductDetails();
                break;
                //addProduct(getProductDetails());
        }
    }

    private String checkID(String productID){
        boolean isValid = true;
        do {
            for (Product product : products){
                if (product.getProductID().equals(productID)){
                    isValid = false;
                    System.out.print("Product ID is already taken please give another ID :");
                    input.next();
                    break;
                }
            }
        }while (!isValid);
        return productID;
    }

    private void getProductDetails() {
        System.out.print("Product ID :\t");
        String productID = checkID(input.next());
        System.out.print("Product Name :\t");
        String productName = input.next();
        System.out.print("Product availability :\t");
        int quantity = InputValidator.getPositiveInt(input.next());
        System.out.print("Product Price :\t");
        double price = InputValidator.getPositiveDouble(input.next());
        System.out.println();

        System.out.println(productID + productName);
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public void printProducts() {

    }

    @Override
    public void saveProductsToFile(String filename) {

    }

    @Override
    public void loadProductsFromFile(String filename) {

    }
}
