//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

import utils.InputValidation;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    //initializing static arraylists to hold entered products and registered users.
    //encapsulating class data
    private static ArrayList<Product> products;
    private static ArrayList<User> users;
    public WestminsterShoppingManager(){
        Scanner scanner = new Scanner(System.in);
        //loading saved product and user data
        products = SerializationAndDeserialization.loadFromFile("products.dat");
        users = SerializationAndDeserialization.loadAccounts();

        String exit="";
        //implementing console menu
        while(!exit.equals("5")){
            System.out.println("_".repeat(50));
            System.out.println("""
                    Select option (enter number of selected option):
                        1.Add new product
                        2.Delete product
                        3.Print product list
                        4.Save to File
                        5.Exit
                        6.Open GUI""");
            System.out.println("_".repeat(50));
            System.out.print("Enter your choice here : ");
            String option = scanner.nextLine();
            System.out.println();

            //using a switch to navigate implementation of menu options
            switch (option){
                case "1":{
                    //making sure no more than 50 products in a system
                    if(products.size()<50){
                        getProductDetails();
                    }else {
                        System.out.print("Sorry! maximum amount of products reached.\n" +
                                "Delete unwanted products to input new products");
                        loadingAnim(200);
                    }
                    break;
                }
                case "2":{
                    getDeleteProductDetails();
                    System.out.println(products.size()+" Products in system.");
                    break;
                }
                case "3":{
                    printProductList();
                    waitTime(3000);
                    break;
                }
                case "4":{
                    SerializationAndDeserialization.saveFile(products, "products.dat");
                    break;
                }
                case "5":{
                    exit="5";
                    break;
                }
                case "6":{
                    User loguser = userAccount();
                    //validating successful login
                    if(loguser!=null){
                        new GUI(loguser);
                    }
                    break;
                }
                default:
                    System.out.println("Invalid input! Please try again...");
                    waitTime(3000);
            }
        }
        //exit message
        System.out.println("Thank you for using this app!\nHave a nice day :)");
    }

// ---------------- 1.Add new product ----------------
    //method used to get product details from user
    public void getProductDetails(){
        System.out.print("""
                    1.Clothing
                    2.Electronics
                Select category of product to add (enter number of selected category) :\s""");

        //validating input
        int category;
        while (true){
            category = InputValidation.intInputValidation();
            if (!(category==1 || category==2))
                System.out.print("Please Enter 1 or 2 only : ");
            else
                break;
        }

        //collecting the detail common to all products first
        System.out.print("Enter product Id: ");
        String productID = checkID(products);
        System.out.print("Enter product name: ");
        String productName = InputValidation.stringInputValidation();
        System.out.print("Enter available stock amount: ");
        int availableAmount = InputValidation.intInputValidation();
        System.out.print("Enter product unit price: ");
        double price = InputValidation.doubleInputValidation();

        //using switch to collect clothing and electronics unique data
        switch (category){
            case 1: {
                System.out.print("Enter clothing size: ");
                String siz = InputValidation.stringInputValidation();
                System.out.print("Enter clothing colour: ");
                String color = InputValidation.stringInputValidation();
                System.out.print("Enter clothing brand: ");
                String brand = InputValidation.stringInputValidation();

                //creating and adding new clothing to a system
                addNewProduct(productID, productName, availableAmount, price, siz, color, brand);
                break;
            }
            case 2: {
                System.out.print("Enter electronics brand: ");
                String brand = InputValidation.stringInputValidation();
                System.out.print("Enter warranty period (\"x years\", \"y months\" or \"z weeks\"): ");
                String warrantyPeriod = InputValidation.warrantyValidation();
                //creating and adding new electronics to a system
                addNewProduct(productID, productName, availableAmount, price, brand, warrantyPeriod);
                break;
            }
        }
        System.out.print("New product added!");
        loadingAnim(200);
    }

    //method to create and add clothing to products array
    public void addNewProduct(String productID, String productName, int availableAmount, double price, String size, String colour, String brand) {
        Clothing cloth = new Clothing(productID, productName, availableAmount, price, size, colour, brand);
        products.add(cloth);
    }
    //method to create and add electronics to products array
    public void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String brand, String warrantyPeriod) {
        Electronics electronic = new Electronics(prdId, prdNm, avlAmt, prc, brand, warrantyPeriod);
        products.add(electronic);
    }

// ---------------- 2.Delete product ----------------
    //implementing delete method to remove added products
    public void getDeleteProductDetails(){
        Scanner scanner = new Scanner(System.in);
        //checking whether product to be deleted exists
        if(products.isEmpty()){
            System.out.print("No products to delete!");
            loadingAnim(200);
        }
        else {
            System.out.print("Enter product Id of product to delete: ");
            String productId = scanner.nextLine();

            deleteProduct(productId);
        }
    }
    //creating method to remove products from a product array based on the input product ID
    public void deleteProduct(String productId) {
        boolean productDeleted=false;
        for(int i=0; i<products.size(); i++){
            if(Objects.equals(products.get(i).getProductID(), productId)){
                products.remove(i);
                productDeleted=true;
                System.out.print("Product "+productId+" successfully deleted!");
                loadingAnim(200);
                break;
            }
        }
        //informing user about product based on given ID not existing
        if (!productDeleted) {
            System.out.print("Product "+productId+" not found!");
            loadingAnim(200);
        }
    }

// ---------------- 3.Print product list ----------------
    //creating method to print products array to string
    public void printProductList() {
        products.sort(Comparator.comparing(Product::getProductID));
        if(products.isEmpty()){
            System.out.print("No products to display!");
            loadingAnim(200);
        }
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

// ---------------- 6.Open GUI ----------------
    //creating method to handle user account related data
    public User userAccount(){
        User userAccount=null;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //giving user the option to log in to previously registered accounts and create new accounts
            System.out.println("""
                    Select option:
                        1.Login to user account
                        2.Create new account""");
            System.out.print("Enter your choice : ");
            String option = scanner.nextLine();
            System.out.println();
            //using switch to implement user login and account creation
            switch (option) {
                case "1": {
                    userAccount = userLogin();
                    break;
                }
                case "2": {
                    userAccount = userCreation();
                    userAccount.changeFirstTime();
                    break;
                }
                default:
                    System.out.print("Invalid input! Please try again");
                    loadingAnim(200);
            }
            if (option.equals("1") || option.equals("2"))
                break;
        }
        return userAccount;
    }
    //creating user login method
    public User userLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username : ");
        String username = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();

        boolean accFound=false;
        User user = null;
        //checking username and password
        for (User value : users) {
            if (Objects.equals(value.getUsername(), username)) {
                accFound = true;
                if (!Objects.equals(value.getPassword(), password)) {
                    System.out.print("Incorrect password");
                    loadingAnim(200);
                } else {
                    user = value;
                    System.out.println("Successfully logged in!");
                }
                break;
            }
        }
        //informing user of an account not existing
        if (!accFound) {
            System.out.print("Account not found");
            loadingAnim(200);
        }
        return user;
    }
    //creating method to create a user account
    public User userCreation(){
        Scanner scanner = new Scanner(System.in);
        //getting new username and password
        String username;
        //making sure of the availability of username
        while(true){
            System.out.print("Enter new username:");
            username = scanner.nextLine();
            boolean usrAva = true;
            for (User user : users) {
                if (Objects.equals(username, user.getUsername())) {
                    usrAva = false;
                    break;
                }
            }
            if(!usrAva){
                System.out.print("Username is already taken! Please try again");
                loadingAnim(200);
            }else{break;}
        }
        //getting new password
        System.out.print("Enter new password : ");
        String password = scanner.nextLine();
        //creating a new account
        User newUser = new User(username, password);
        System.out.println("Successfully create account!\n" +
                "   Account username is "+ username +
                "   Account password is "+ password);
        users.add(newUser);
        SerializationAndDeserialization.saveAccounts(users);
        return newUser;
    }

    //creating method to sort products based on product ID and return a sorted array
    public static ArrayList<Product> getProductsArray(){
        products.sort(Comparator.comparing(Product::getProductID));
        return products;
    }

    private static void waitTime(int time){
        try {
            Thread.sleep(time); // Pause for 1 second (1000 milliseconds)
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void loadingAnim(int time){
        waitTime(time);
        System.out.print(".");
        waitTime(time);
        System.out.print(".");
        waitTime(time);
        System.out.println(".");
        waitTime(time);
    }

    public String checkID(ArrayList<Product> products){
        Scanner scanner = new Scanner(System.in);
        String productID;

        do {
            productID = scanner.nextLine();
            boolean isValid = true;

            for (Product product : products) {
                if (Objects.equals(product.getProductID(), productID) || productID.isEmpty()) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.print("Product ID is already taken. Please enter a different ID: ");
            } else {
                return productID;
            }
        } while (true);
    }
}