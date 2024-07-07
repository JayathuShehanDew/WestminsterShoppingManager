package main;

import java.util.ArrayList;
import java.util.List;

public interface ShoppingManager {
    List<Product> products= new ArrayList<>();

    void addProduct(Product product);
    void deleteProduct(String productId);
    void printProducts();
    void saveProductsToFile(String filename);
    void loadProductsFromFile(String filename);
}