import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1506900153016872262L;

    // encapsulating class attributes
    private final String username;
    private final String password;
    private final ShoppingCart cart;
    private boolean firstTime = false;

    // creating class constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ShoppingCart();
    }

    // creating getters and setters
    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void addToUserCart(Product product) {
        cart.addToCart(product);
    }

    public ArrayList<Product> getUserCartProductList() {
        return cart.getCartProductsArray();
    }

    // creating methods to manipulate boolean firstTime to facilitate discount calculations
    public void changeFirstTime() {
        this.firstTime = true;
    }

    public boolean userFirstTime() {
        return firstTime;
    }

    public ShoppingCart getCart() {
        return this.cart;
    }
}
