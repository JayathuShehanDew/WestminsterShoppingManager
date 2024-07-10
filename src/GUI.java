//IIT No: 20220332
//UoW No: w1953207
//Name  : D. K. J. S. Dewmina

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private static ArrayList<Product> productList;
    static ArrayList<Product> currentList;
    private final JTextArea textArea = new JTextArea("");
    private final JButton shoppingCartBtn = new JButton("Shopping Cart");
    private final JButton addToShoppingCartBtn = new JButton("Add to Shopping Cart");
    private final JComboBox<String> productType;
    private final DefaultTableModel tableModel;
    private int tableShow = 0;
    private int selectedRow = 0;
    private final User user;
    private Product selectedProductWhole;
    JFrame mainFrame = new JFrame();

    public GUI(User currentUser) {
        productList = WestminsterShoppingManager.getProductsArray();
        user = currentUser;
        mainFrame.setTitle("Westminster Shopping Centre");
        mainFrame.setSize(700, 700);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(700, 350));
        topPanel.setBounds(0, 0, 700, 350);
        topPanel.setLayout(null);
        mainFrame.add(topPanel);
        JLabel label1 = new JLabel("Select Product Category");
        topPanel.add(label1);
        label1.setBounds(10, 25, 200, 20);
        String[] productTypes = {"All", "Electronics", "Clothing"};
        productType = new JComboBox<>(productTypes);
        productType.addActionListener(this);
        topPanel.add(productType);
        productType.setBounds(220, 20, 200, 30);
        topPanel.add(shoppingCartBtn);
        shoppingCartBtn.setPreferredSize(new Dimension(100, 20));
        shoppingCartBtn.setFocusable(false);
        shoppingCartBtn.setBounds(450, 20, 200, 30);
        shoppingCartBtn.addActionListener(this);
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price(£)");
        tableModel.addColumn("Info");
        addTableRows(tableShow);
        JTable productsTable = new JTable(tableModel);
        productsTable.setRowHeight(60);
        productsTable.setDefaultRenderer(Object.class, new CustomCellRenderer());

        // Set selection mode to select rows
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productsTable.setRowSelectionAllowed(true);
        productsTable.setColumnSelectionAllowed(false);

        JScrollPane scrollPane = new JScrollPane(productsTable);
        topPanel.add(scrollPane);
        scrollPane.setBounds(10, 60, 670, 280);
        ListSelectionModel model = productsTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!model.isSelectionEmpty()) {
                    addToShoppingCartBtn.setEnabled(true);
                    selectedRow = model.getMinSelectionIndex();
                    textArea.setText(getSelectedRowText(selectedRow));
                }
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(700, 350));
        bottomPanel.setBounds(0, 350, 700, 350);
        bottomPanel.setLayout(null);
        mainFrame.add(bottomPanel);
        JLabel label2 = new JLabel("Selected Product - Details");
        bottomPanel.add(label2);
        label2.setFont(new Font("Calibri", Font.BOLD, 18));
        label2.setBounds(30, 5, 300, 30);
        bottomPanel.add(textArea);
        textArea.setBounds(30, 35, 600, 200);
        textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
        textArea.setBackground(null);
        bottomPanel.add(addToShoppingCartBtn);
        addToShoppingCartBtn.setPreferredSize(new Dimension(100, 20));
        addToShoppingCartBtn.setFocusable(false);
        addToShoppingCartBtn.setBounds(200, 280, 300, 30);
        addToShoppingCartBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shoppingCartBtn) {
            new ShoppingCartGUI(user);
        } else if (e.getSource() == productType) {
            tableShow = productType.getSelectedIndex();
            int m = tableModel.getRowCount();
            for (int i = 0; i < m; i++) {
                tableModel.removeRow(0);
            }
            addTableRows(tableShow);
        } else if (e.getSource() == addToShoppingCartBtn) {
            if (selectedProductWhole.getAvailableAmount() != 0) {
                user.addToUserCart(selectedProductWhole);
                selectedProductWhole.setAvailableAmount(selectedProductWhole.getAvailableAmount() - 1);
            }
            textArea.setText(getSelectedRowText(selectedRow));
        }
    }

    public ArrayList<Product> getProducts(int ProductType) {
        ArrayList<Product> temp = new ArrayList<>();
        switch (ProductType) {
            case 0: {
                temp.addAll(productList);
                break;
            }
            case 1: {
                for (Product product : productList) {
                    if (product instanceof Electronics) {
                        temp.add(product);
                    }
                }
                break;
            }
            case 2: {
                for (Product product : productList) {
                    if (product instanceof Clothing) {
                        temp.add(product);
                    }
                }
                break;
            }
        }
        return temp;
    }

    public void addTableRows(int productType) {
        ArrayList<Product> tempArray = getProducts(productType);
        currentList = getProducts(productType);
        String[] list = new String[5];
        for (Product product : tempArray) {
            list[0] = product.getProductID();
            list[1] = product.getProductName();
            list[2] = product.getType();
            list[3] = String.valueOf(product.getPrice());
            list[4] = product.getInfo();
            tableModel.addRow(list);
        }
    }

    public String getSelectedRowText(int rowNumber) {
        Product selectedProduct = currentList.get(rowNumber);
        selectedProductWhole = selectedProduct;
        if (selectedProduct.getAvailableAmount() == 0)
            addToShoppingCartBtn.setEnabled(false);
        return selectedProduct.getProductDetails();
    }
}

class CustomCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Product product = GUI.currentList.get(row);
        if (product.getAvailableAmount() < 3) {
            cell.setBackground(Color.RED);
        } else {
            cell.setBackground(Color.WHITE);
        }
        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
        }
        return cell;
    }
}
