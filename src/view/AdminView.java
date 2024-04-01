package view;

import controller.AdminController;

import classes.User;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JPanel {
    JFrame frame = new JFrame("Admin Console");
    AdminController controller = new AdminController(this);

    User currentUser;

    JPanel buttonsPanel, bottomPanel;
    JButton userButton, productButton, salesButton, logOutButton, backButton;

    public AdminView(User user, Point location) {
        this.currentUser = user;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Buttons
        buttonsPanel = new JPanel();
        userButton = new JButton("Manage Users");
        productButton = new JButton("Manage Products");
        salesButton = new JButton("Search Sales");
        logOutButton = new JButton("Log Out");

        userButton.addActionListener(e -> this.controller.gotoManageUsers());
        productButton.addActionListener(e -> this.controller.gotoManageProducts());
        salesButton.addActionListener(e -> this.controller.gotoSales());
        logOutButton.addActionListener(e -> this.controller.logOut());

        buttonsPanel.add(userButton);
        buttonsPanel.add(productButton);
        buttonsPanel.add(salesButton);
        buttonsPanel.add(logOutButton);

        /// Bottom
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        bottomPanel.setPreferredSize(new Dimension(0, 50));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> this.controller.goBack());
        bottomPanel.add(backButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonsPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocation(location);
        frame.setVisible(true);
    }

    public void dispose(){
        frame.dispose();
    }

    public Point getLocation(){ return frame.getLocation();}

    public User getUser(){
        return this.currentUser;
    }
}
