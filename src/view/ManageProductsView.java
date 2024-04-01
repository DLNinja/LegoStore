package view;

import classes.User;
import controller.ManageProductsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageProductsView {
    JFrame frame = new JFrame("Manage Products");
    ManageProductsController controller = new ManageProductsController(this);

    User currentUser;

    JPanel inputsPanel, tablePanel, bottomPanel;
    JLabel codeLabel, nameLabel, categoryLabel, priceLabel, stockLabel, minAgeLabel, noPiecesLabel, instructionLabel;
    JTextField codeTextField, nameTextField, priceTextField, stockTextField, minAgeTextField, noPiecesTextField;
    JComboBox<String> productCategoryComboBox;
    JButton backButton, createProductButton, updateProductButton, deleteProductButton, clearButton;
    Dimension labelDimension = new Dimension(60, 20), codeLabelDim = new Dimension(100, 20),
            codeInputBoxDim = new Dimension(140, 20), inputBoxDimension = new Dimension(180, 20),
            inputPanelDimension = new Dimension((int)(labelDimension.getWidth() + inputBoxDimension.getWidth()) + 20, 0),
            tableDimension = new Dimension(700, 500), buttonsDimension = new Dimension(240, 25);
    DefaultTableModel tableModel;
    JTable productDataTable;
    JScrollPane scrollPane;
    Object[][] productData;
    String[] tableColumns;

    public ManageProductsView(User user, Point loc) {
        this.currentUser = user;

        /// Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        inputsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        inputsPanel.setPreferredSize(inputPanelDimension);
        inputsPanel.setBackground(Color.white);

        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.white);

        /// Inputs Panel

        codeLabel = new JLabel("Product Code");
        codeTextField = new JTextField();
        codeLabel.setPreferredSize(codeLabelDim);
        codeTextField.setPreferredSize(codeInputBoxDim);
        inputsPanel.add(codeLabel);
        inputsPanel.add(codeTextField);

        nameLabel = new JLabel("Name");
        nameTextField = new JTextField();
        nameLabel.setPreferredSize(labelDimension);
        nameTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(nameLabel);
        inputsPanel.add(nameTextField);

        priceLabel = new JLabel("Price");
        priceTextField = new JTextField();
        priceLabel.setPreferredSize(labelDimension);
        priceTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(priceLabel);
        inputsPanel.add(priceTextField);

        categoryLabel = new JLabel("Category");
        categoryLabel.setPreferredSize(labelDimension);
        categoryLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        inputsPanel.add(categoryLabel);

        productCategoryComboBox = new JComboBox<>(this.controller.getProductCategories().toArray(new String[0]));
        productCategoryComboBox.setPreferredSize(inputBoxDimension);
        productCategoryComboBox.setFocusable(false);
        inputsPanel.add(productCategoryComboBox);

        stockLabel = new JLabel("Stock");
        stockTextField = new JTextField();
        stockLabel.setPreferredSize(labelDimension);
        stockTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(stockLabel);
        inputsPanel.add(stockTextField);

        minAgeLabel = new JLabel("Min Age");
        minAgeTextField = new JTextField();
        minAgeLabel.setPreferredSize(labelDimension);
        minAgeTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(minAgeLabel);
        inputsPanel.add(minAgeTextField);

        noPiecesLabel = new JLabel("No Pieces");
        noPiecesTextField = new JTextField();
        noPiecesLabel.setPreferredSize(labelDimension);
        noPiecesTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(noPiecesLabel);
        inputsPanel.add(noPiecesTextField);

        /// Inputs Buttons
        createProductButton = new JButton("Create Product");
        createProductButton.setPreferredSize(buttonsDimension);
        inputsPanel.add(createProductButton);

        updateProductButton = new JButton("Update Product");
        updateProductButton.setPreferredSize(buttonsDimension);
        inputsPanel.add(updateProductButton);

        instructionLabel = new JLabel("Select from table to delete");
        instructionLabel.setFont(new Font("Calibri", Font.BOLD, 10));
        inputsPanel.add(instructionLabel);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setPreferredSize(buttonsDimension);
        inputsPanel.add(deleteProductButton);

        clearButton = new JButton("Clear Fields");
        clearButton.setPreferredSize(buttonsDimension);
        inputsPanel.add(clearButton);

        createProductButton.addActionListener(e -> this.controller.createProduct());
        updateProductButton.addActionListener(e -> this.controller.updateProduct());
        deleteProductButton.addActionListener(e -> this.controller.deleteProduct());
        clearButton.addActionListener(e -> clearFields());

        /// Table
        productData = this.controller.getProductsDataTable();
        tableColumns = new String[]{"Code", "Name", "Category", "Price", "Stock", "Min. Age", "No Pieces"};
        tableModel = new DefaultTableModel(productData, tableColumns);

        productDataTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productDataTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                    codeTextField.setText((productData[productDataTable.getSelectedRow()][0]).toString());
                    nameTextField.setText((String) productData[productDataTable.getSelectedRow()][1]);
                    productCategoryComboBox.setSelectedItem(productData[productDataTable.getSelectedRow()][2]);
                    priceTextField.setText((String) productData[productDataTable.getSelectedRow()][3]);
                    stockTextField.setText((String) productData[productDataTable.getSelectedRow()][4]);
                    minAgeTextField.setText((String) productData[productDataTable.getSelectedRow()][5]);
                    noPiecesTextField.setText((String) productData[productDataTable.getSelectedRow()][6]);
                }
            }
        });

        scrollPane = new JScrollPane(productDataTable);
        scrollPane.setPreferredSize(tableDimension);
        tablePanel.add(scrollPane, new GridBagConstraints());

        /// Bottom
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        bottomPanel.setBackground(Color.white);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> this.controller.goBack());
        bottomPanel.add(backButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputsPanel, BorderLayout.WEST);
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocation(loc);
        frame.setVisible(true);
    }

    public User getUser(){
        return this.currentUser;
    }

    public boolean checkInputs(){
        return (codeTextField.getText().isBlank() || nameTextField.getText().isBlank() ||
                priceTextField.getText().isBlank() || stockTextField.getText().isBlank() ||
                minAgeTextField.getText().isBlank() || noPiecesTextField.getText().isBlank()
                || productCategoryComboBox.getSelectedItem() == null);
    }

    void clearFields(){
        codeTextField.setText("");
        nameTextField.setText("");
        priceTextField.setText("");
        productCategoryComboBox.setSelectedItem(null);
        stockTextField.setText("");
        minAgeTextField.setText("");
        noPiecesTextField.setText("");
    }

    public int getCode(){
        return Integer.parseInt(codeTextField.getText());
    }

    public String getName(){
        return nameTextField.getText();
    }

    public int getPrice(){
        return Integer.parseInt(priceTextField.getText());
    }

    public String getCategory(){
        return (String) productCategoryComboBox.getSelectedItem();
    }

    public int getStock(){
        return Integer.parseInt(stockTextField.getText());
    }

    public int getMinAge(){
        return Integer.parseInt(minAgeTextField.getText());
    }

    public int getNoPieces(){
        return Integer.parseInt(noPiecesTextField.getText());
    }

    public void updateProductTable(){
        productData = this.controller.getProductsDataTable();
        int currentRowCount = tableModel.getRowCount();
        tableModel.setRowCount(0);
        tableModel.setRowCount(currentRowCount);
        tableModel.setDataVector(productData, tableColumns);
    }

    public void showError(String message){
        JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
    }

    public void dispose(){
        frame.dispose();
    }

    public Point getLocation(){
        return frame.getLocation();
    }
}
