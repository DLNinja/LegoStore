package view;

import classes.User;
import controller.ManageUsersController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManageUsersView extends JPanel{

    JFrame frame = new JFrame("Manage Users");
    ManageUsersController controller = new ManageUsersController(this);

    User currentUser;

    JPanel inputsPanel, tablePanel, bottomPanel;
    JLabel usernameLabel, passwordLabel,  userStatusLabel, instructionLabel, idLabel;
    JTextField usernameTextField, passwordTextField, idTextField;
    JComboBox<String> userStatusComboBox;
    JButton backButton, createUserButton, updateUserButton, deleteUserButton, clearButton;
    Dimension labelDimension = new Dimension(60, 20), inputBoxDimension = new Dimension(180, 20),
            inputPanelDimension = new Dimension((int)(labelDimension.getWidth() + inputBoxDimension.getWidth()) + 20, 0),
            tableDimension = new Dimension(700, 500);
    DefaultTableModel tableModel;
    JTable userDataTable;
    JScrollPane scrollPane;
    Object[][] userData;
    String[] tableColumns;

    public ManageUsersView(User user, Point loc) {
        this.currentUser = user;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Frame
        inputsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        inputsPanel.setPreferredSize(inputPanelDimension);
        inputsPanel.setBackground(Color.white);

        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.white);

        /// Inputs Panel
        idLabel = new JLabel("ID");
        idTextField = new JTextField();
        idLabel.setPreferredSize(labelDimension);
        idTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(idLabel);
        inputsPanel.add(idTextField);

        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField();
        usernameLabel.setPreferredSize(labelDimension);
        usernameTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(usernameLabel);
        inputsPanel.add(usernameTextField);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField();
        passwordLabel.setPreferredSize(labelDimension);
        passwordTextField.setPreferredSize(inputBoxDimension);
        inputsPanel.add(passwordLabel);
        inputsPanel.add(passwordTextField);

        userStatusLabel = new JLabel("Status");
        userStatusLabel.setPreferredSize(labelDimension);
        userStatusLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        inputsPanel.add(userStatusLabel);

        userStatusComboBox = new JComboBox<>(new String[]{null, "Manager", "User"});
        userStatusComboBox.setPreferredSize(inputBoxDimension);
        userStatusComboBox.setFocusable(false);
        inputsPanel.add(userStatusComboBox);

        /// Input Buttons
        createUserButton = new JButton("Create user");
        inputsPanel.add(createUserButton);

        updateUserButton = new JButton("Update User");
        inputsPanel.add(updateUserButton);

        instructionLabel = new JLabel("Select from table to delete");
        instructionLabel.setFont(new Font("Calibri", Font.BOLD, 10));
        inputsPanel.add(instructionLabel);

        deleteUserButton = new JButton("Delete User");
        inputsPanel.add(deleteUserButton);

        clearButton = new JButton("Clear Fields");
        inputsPanel.add(clearButton);


        createUserButton.addActionListener(e -> this.controller.createUser());
        updateUserButton.addActionListener(e -> this.controller.updateUser());
        deleteUserButton.addActionListener(e -> this.controller.deleteUser());
        clearButton.addActionListener(e -> clearFields());

        /// Table
        userData = this.controller.getUsersDataTable();
        tableColumns = new String[]{"ID", "Username", "Password", "Status"};
        tableModel = new DefaultTableModel(userData, tableColumns);

        userDataTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userDataTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                    idTextField.setText((userData[userDataTable.getSelectedRow()][0]).toString());
                    usernameTextField.setText((String) userData[userDataTable.getSelectedRow()][1]);
                    passwordTextField.setText((String) userData[userDataTable.getSelectedRow()][2]);
                    userStatusComboBox.setSelectedItem(userData[userDataTable.getSelectedRow()][3]);
                }
            }
        });

        scrollPane = new JScrollPane(userDataTable);
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
        return (usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank()
                || userStatusComboBox.getSelectedItem() == null);
    }

    void clearFields(){
        idTextField.setText("");
        usernameTextField.setText("");
        passwordTextField.setText("");
        userStatusComboBox.setSelectedItem(null);
    }

    public int getIndex(){
        return Integer.parseInt(idTextField.getText());
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public String getStatus(){
        return (String)userStatusComboBox.getSelectedItem();
    }

    public void updateUsersTable(){
        userData = this.controller.getUsersDataTable();
        int currentRowCount = tableModel.getRowCount();
        tableModel.setRowCount(0);
        tableModel.setRowCount(currentRowCount);
        tableModel.setDataVector(userData, tableColumns);
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
