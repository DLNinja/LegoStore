package view;

import classes.User;
import controller.SalesController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesView {
    JFrame frame = new JFrame("Manage Sales");
    SalesController controller = new SalesController(this);

    User currentUser;

    JPanel tablePanel, bottomPanel;
    JButton backButton;
    Dimension tableDimension = new Dimension(700, 500);
    DefaultTableModel tableModel;
    JTable saleDataTable;
    JScrollPane scrollPane;
    Object[][] saleData;
    String[] tableColumns;

    public SalesView(User user, Point loc) {
        this.currentUser = user;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Frame
        tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.white);

        /// Table
        saleData = this.controller.getSalesDataTable();
        tableColumns = new String[]{"Sale Id", "User Id", "Product Id", "Product Count", "Total Cost"};
        tableModel = new DefaultTableModel(saleData, tableColumns);

        saleDataTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane = new JScrollPane(saleDataTable);
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
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocation(loc);
        frame.setVisible(true);
    }

    public User getUser(){
        return this.currentUser;
    }

    public void dispose(){
        frame.dispose();
    }

    public Point getLocation(){
        return frame.getLocation();
    }
}
