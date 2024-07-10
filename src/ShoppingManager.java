//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

//creating an interface for shopping manager
public interface ShoppingManager {
    void addNewProduct(String productID, String productName, int availableAmount, double price, String size, String colour, String brand);
    void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String brand, String warrantyPeriod);
    void deleteProduct(String productId);
    void printProductList();
}
