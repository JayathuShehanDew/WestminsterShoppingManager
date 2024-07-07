//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

import java.util.ArrayList;

//creating an interface for shopping manager
public interface ShoppingManager {
    void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String siz, String color, String brnd);
    void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String brnd, String warntPrd);
    void deleteProduct(String productId);
    void printProductList();
    void saveToFile(ArrayList<Product> products, String fileName);
}
