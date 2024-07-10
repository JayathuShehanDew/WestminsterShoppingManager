//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

//implementing electronics child class of product class
import java.io.Serializable;
import java.util.ArrayList;
//creating shoppingCart class to record purchase data
public class ShoppingCart implements Serializable {
    //encapsulating class attributes
    private final ArrayList<Product> cartProducts;
    //creating getters and setters
    public ShoppingCart(){
        cartProducts = new ArrayList<>();
    }
    public ArrayList<Product> getCartProductsArray(){
        return cartProducts;
    }
    //creating method to add products to cart
    public void addToCart(Product product){
        boolean bought=false;
        //checking whether the same product has been bought before
        for (Product cartProduct : cartProducts) {
            if (cartProduct == product) {
                //if product bought before increase bought quantity
                cartProduct.productBought();
                bought = true;
            }
        }
        if(!bought){
           cartProducts.add(product);
           product.productBought();
        }
    }
    //method to calculate total price of products in cart without discounts
    public double getTotalPrice(){
        double total=0;
        Product product;
        for (Product cartProduct : this.cartProducts) {
            product = cartProduct;
            total = total + (product.getPrice()*product.getQuantityBought());
        }
        return total;
    }
    //checking eligibility of getting discount for buying 3 or more products of the same category
    public boolean getDiscountEligibility(){
        boolean discount = false;
        int clothNum=0;
        int elcNum=0;
        for (Product cartProduct : cartProducts) {
            if (cartProduct instanceof Clothing) {
                clothNum = cartProduct.getQuantityBought() + clothNum;
            } else {
                elcNum = cartProduct.getQuantityBought() + clothNum;
            }
        }
        if(clothNum>2 || elcNum>2){
            discount = true;
        }
        return discount;
    }
    //method to calculate final price with discounts
    public double getFinalPrice(User user){
        boolean firstTime= user.userFirstTime();
        boolean discount=user.getCart().getDiscountEligibility();
        double total=getTotalPrice();
        if(discount && !firstTime) {
            total = total * 0.8;
        }
        if(discount && firstTime){
            total = total * 0.7;
        }
        if(!discount && firstTime){
            total = total * 0.9;
        }
        return total;
    }
}
