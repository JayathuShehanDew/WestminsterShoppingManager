//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
//creating GUI for shopping class. initiate upon selection of shopping cart button from GUI class
public class ShoppingCartGUI extends JFrame {

    //creating the main method of the shopping cart GUI.
    //gets a user account of current user
    ShoppingCartGUI(User gotUser){
        //implementing user to user attribute
        //creating the main frame
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Shopping Cart");
        mainFrame.setSize(700,700);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(null);
        //making frame un-resizable for easier handling of components
        mainFrame.setResizable(false);
        //creating default table for products added to cart
        //encapsulating and initializing class attributes
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Price");
        //adding products selected to be added to the cart into the table
        ArrayList<Product> tempArray = gotUser.getUserCartProductList();
        String[] list = new String[3];
        for (Product product : tempArray) {
            String productDetails = product.getProductID() + "\n" + product.getProductName() + "\n" + product.getInfo();
            list[0] = productDetails;
            list[1] = String.valueOf(product.getQuantityBought());
            list[2] = String.valueOf(product.getPrice() * product.getQuantityBought());
            tableModel.addRow(list);
        }
        //creating JTable using the before made default table model
        JTable productsTable = new JTable(tableModel);
        productsTable.setRowHeight(60);
        //adding JScrollPane to created table
        JScrollPane scrollPane = new JScrollPane(productsTable);
        mainFrame.add(scrollPane);
        scrollPane.setBounds(10,10,670,350);
        //changing the first column to a text area type
        productsTable.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
        //adding text area displaying price details to the main frame
        JTextArea textArea = new JTextArea("");
        mainFrame.add(textArea);
        textArea.setBounds(30,370,600,270);
        textArea.setFont(new Font("Calibri",Font.PLAIN,18));
        textArea.setText(billText(gotUser));
    }
    //creating method to generate the text related to a total bill
    public String billText(User user){
        String total ="                                          Total      "+user.getCart().getTotalPrice()+"£";
        String frtDis="\n                  First Purchase Discount (10%)     -"+(user.getCart().getTotalPrice()*0.1)+"£";
        String itmDis="\n    Three Items in same Category Discount (20%)     -"+(user.getCart().getTotalPrice()*0.2)+"£";
        String finPrc="\n\n                                    Final Total      "+user.getCart().getFinalPrice(user)+"£";
        String finString="";
        boolean itemDiscount = user.getCart().getDiscountEligibility();
        //applying text based on discounts available to user
        if(itemDiscount && user.userFirstTime()){
            finString=total+frtDis+itmDis+finPrc;
        }
        if(!itemDiscount && user.userFirstTime()){
            finString=total+frtDis+finPrc;
        }
        if(itemDiscount && !user.userFirstTime()){
            finString=total+itmDis+finPrc;
        }
        if(!itemDiscount && !user.userFirstTime()){
            finString=total+finPrc;
        }
        return finString;
    }
    //Methods related to changing JTable column to textArea.
    // Reference included below,
//    docs.oracle.com.
//    (n.d.).
//    TableCellRenderer (Java Platform SE 8).
//    [online] Available at:
//    https://docs.oracle.com/javase/8/docs/api/javax/swing/table/TableCellRenderer.html [Accessed 12 Jan. 2024].
    static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }
}
