package view;

import classes.Product;
import classes.User;
import controller.ProductViewController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProductPageView {
    JFrame frame = new JFrame("Buy Product");
    ProductViewController controller = new ProductViewController(this);

    private static final String IMAGE_FOLDER_PATH = "src/photos/";
    private static final String DEFAULT_IMAGE_PATH = "src/photos/LEGO_logo.svg";

    Product currentProduct;
    User currentUser;

    JPanel imagePanel, detailsPanel, buyPanel, bottomPanel;
    JLabel nameLabel, categoryLabel, priceLabel, stockLabel, ageLabel, piecesLabel, buyLabel;
    JButton backButton, minusButton, plusButton, buyButton;
    Dimension labelDimension = new Dimension(60, 20), inputBoxDimension = new Dimension(180, 20),
            inputPanelDimension = new Dimension((int)(labelDimension.getWidth() + inputBoxDimension.getWidth()) + 20, 0),
            tableDimension = new Dimension(700, 500), buttonsDimension = new Dimension(105, 25);
    Color mainColor = Color.white, inputColor = Color.black;
    Dimension imageSize = new Dimension(400, 400);
    private int buyCount = 0;

    public ProductPageView(User user, Product product, Point loc){
        this.currentProduct = product;
        this.currentUser = user;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Image
        imagePanel = new JPanel();
        ImageIcon productImage = loadImage(String.valueOf(this.currentProduct.getCode()), imageSize);
        imagePanel.add(new JLabel(productImage));

        /// Details Panel
        categoryLabel = new JLabel(this.currentProduct.getCategory() + " | Code: " + this.currentProduct.getCode());
        categoryLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        categoryLabel.setForeground(new Color(120, 120, 120));
        nameLabel = new JLabel(this.currentProduct.getName());
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 22));
        priceLabel = new JLabel(this.currentProduct.getPrice() + " Lei");
        priceLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        stockLabel = new JLabel("Stock: " + this.currentProduct.getStock());
        stockLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        ageLabel = new JLabel("Recommended age " + this.currentProduct.getMinAge() + "+");
        ageLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        piecesLabel = new JLabel(this.currentProduct.getNoPieces() + " Pieces");
        piecesLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setAlignmentY(0);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.add(categoryLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detailsPanel.add(priceLabel);
        detailsPanel.add(stockLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detailsPanel.add(piecesLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        detailsPanel.add(ageLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buyPanel = new JPanel(new FlowLayout());
        minusButton = new JButton("-");
        plusButton = new JButton("+");
        buyButton = new JButton("Buy");
        buyButton.setBackground(Color.GREEN);
        buyLabel = new JLabel(String.valueOf(this.buyCount));
        buyPanel.add(minusButton);
        buyPanel.add(buyLabel);
        buyPanel.add(plusButton);
        buyPanel.add(buyButton);

        detailsPanel.add(buyPanel);
        detailsPanel.add(Box.createVerticalGlue());

        minusButton.addActionListener(e -> this.controller.changeBuyCount(0));
        plusButton.addActionListener(e -> this.controller.changeBuyCount(1));
        buyButton.addActionListener(e -> this.controller.buyProduct(this.currentProduct));

        /// Bottom
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        bottomPanel.setBackground(mainColor);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> this.controller.goBack());
        bottomPanel.add(backButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imagePanel, BorderLayout.WEST);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocation(loc);
        frame.setVisible(true);
    }

    public User getUser(){
        return this.currentUser;
    }

    public Product getProduct(){
        return this.currentProduct;
    }

    public void setCurrentProduct(Product product){ this.currentProduct = product;}

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int newCount) {
        this.buyCount = newCount;
    }

    public void updateDetails(){
        this.buyLabel.setText(String.valueOf(buyCount));
        this.stockLabel.setText("Stock: " + this.currentProduct.getStock());
    }

    public int getStock() { return Integer.parseInt(this.currentProduct.getStock()); }

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
