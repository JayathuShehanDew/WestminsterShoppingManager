package main;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    static List<Product> productsInCart = new ArrayList<>();

    public static void addProduct(Product addProduct){
        productsInCart.add(addProduct);
        System.out.println("Product Added Successfully");
    }

    public static void removeProduct(Product removeProduct){
        for (Product product : productsInCart){
            productsInCart.remove(product);
        }
    }

    public static double calculateTotal(){
        double total = 0;
        for (Product product : productsInCart){
            total += product.getPrice();
        }
        return total;
    }
}
