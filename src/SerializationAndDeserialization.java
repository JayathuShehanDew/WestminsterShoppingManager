import java.io.*;
import java.util.ArrayList;

public class SerializationAndDeserialization {
    public static void saveFile(ArrayList<Product> products, String fileName){
        // Serialization
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(products);

            out.close();
            fileOut.close();

            System.out.println("Saved successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Product> loadFromFile(String fileName) {
        // Deserialization
        ArrayList<Product> products = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            products = (ArrayList<Product>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.print("No any saved items please add product and save");
            WestminsterShoppingManager.loadingAnim(200);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return products;
    }

    //creating method to save account details to an external file
    public static void saveAccounts(ArrayList<User> users){
        // Serialization
        try {
            FileOutputStream fileOut = new FileOutputStream("userAccounts.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(users);

            out.close();
            fileOut.close();

            System.out.println("Account Saved successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //creating method to loading user accounts from external file
    public static ArrayList<User> loadAccounts(){
        // Deserialization
        ArrayList<User> users = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream("userAccounts.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            users = (ArrayList<User>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.print("No any saved accounts please register first");
            WestminsterShoppingManager.loadingAnim(200);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return users;
    }
}
