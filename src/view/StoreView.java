package view;

import classes.Product;
import controller.StoreController;
import classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class StoreView extends JPanel {
    JFrame frame = new JFrame("Lego Store");
    StoreController controller = new StoreController(this);
    User currentUser;

    JLabel nameLabel;
    JPanel topBar, emptyPanel;
    JScrollPane productScrollPane;
    JButton adminButton, logoutButton;
    Dimension nameLabelDim = new Dimension(150, 20), emptyPanelDim = new Dimension(600, 20), buttonDim = new Dimension(100, 20);

    public StoreView(User user, Point loc) {
        this.currentUser = user;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Top bar
        topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(1000, 30));
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        nameLabel = new JLabel("Welcome, " + this.currentUser.getUsername());
        nameLabel.setPreferredSize(nameLabelDim);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(emptyPanelDim);
        adminButton = new JButton("Console");
        adminButton.setPreferredSize(buttonDim);
        logoutButton = new JButton("Log Out");
        logoutButton.setPreferredSize(buttonDim);
        topBar.add(nameLabel);
        topBar.add(emptyPanel);
        if(Objects.equals(currentUser.getStatus(), "Manager")){
            topBar.add(adminButton);
        }
        topBar.add(logoutButton);

        logoutButton.addActionListener(e -> this.controller.logOut());
        adminButton.addActionListener(e -> this.controller.gotoAdminConsole());

        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);

        /// Scroll Pane
        productScrollPane = createProductScrollPane();

        frame.add(productScrollPane, BorderLayout.CENTER);
        frame.setLocation(loc);
        frame.setVisible(true);
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

    public User getUser(){
        return this.currentUser;
    }

    /// Create the scroll pane to display multiple product cards
    private JScrollPane createProductScrollPane() {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 3, 20, 20));

        ArrayList<Product> productList = this.controller.getProducts();
        for (Product product: productList) {
            ProductCard productCard = new ProductCard(product, this.controller);
            productPanel.add(productCard);
        }

        return new JScrollPane(productPanel);
    }
}

class ProductCard extends JPanel {
    private static final String IMAGE_FOLDER_PATH = "src/photos/";
    private static final String DEFAULT_IMAGE_PATH = "src/photos/LEGO_logo.svg";
    private String name, category, price;
    private int code;

    Dimension imageSize = new Dimension(300, 200);
    JPanel detailsPanel;
    JLabel nameLabel, priceLabel, categoryLabel;

    /// Creates cards with product information to be displayed on the main store page
    public ProductCard(Product product, StoreController controller) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.code = product.getCode();
        this.category = product.getCategory();
        setLayout(new BorderLayout());
        setBorder(createShadowBorder());

        ImageIcon productImage = loadImage(String.valueOf(code), imageSize);
        nameLabel = new JLabel(this.name);
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        categoryLabel = new JLabel(this.category + " | Code: " + this.code);
        categoryLabel.setFont(new Font("Calibri", Font.BOLD, 12));
        categoryLabel.setForeground(new Color(120, 120, 120));
        priceLabel = new JLabel(this.price + " Lei");
        priceLabel.setFont(new Font("Calibri", Font.BOLD, 15));

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(categoryLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalGlue());

        add(new JLabel(productImage), BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event) {
                controller.gotoProductPage(product);
            }
        });
    }

    /// Load product image
    private ImageIcon loadImage(String imageName, Dimension size) {
        String imagePath = IMAGE_FOLDER_PATH + imageName + ".jpg";
        try {
            Image image = ImageIO.read(new File(imagePath));
            return new ImageIcon(image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
        } catch (IOException | IllegalArgumentException e) {
            ImageIcon image = new ImageIcon(DEFAULT_IMAGE_PATH);
            return new ImageIcon(image.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
        }
    }

    /// Create shadow around card
    private Border createShadowBorder() {
        float size = 5.0f;
        Color color = new Color(0, 0, 0, 10);

        Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        Border shadow = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, color),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, color),
                        border));

        return BorderFactory.createCompoundBorder(shadow, border);
    }

}