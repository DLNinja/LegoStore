package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel{
    JFrame frame = new JFrame("LogIn");
    LoginController controller = new LoginController(this);

    JPanel panel, panel1, panel2, panel3;

    JLabel labelParam1 = new JLabel("Username: ");
    JTextField textFieldParam1, textFieldParam2;
    JLabel labelParam2 = new JLabel("Password");
    JButton loginButton = new JButton("Login"), signupButton = new JButton("Go to Signup");

    public LoginView(Point location) {
        /// Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        /// Inputs Panel
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        textFieldParam1 = new JTextField();
        textFieldParam1.setPreferredSize(new Dimension(100, 20));
        textFieldParam2 = new JTextField();
        textFieldParam2.setPreferredSize(new Dimension(100, 20));

        panel1.add(labelParam1);
        panel1.add(textFieldParam1);
        panel2.add(labelParam2);
        panel2.add(textFieldParam2);

        loginButton.addActionListener(e -> this.controller.gotoApp());
        panel3.add(loginButton);

        signupButton.addActionListener(e -> this.controller.gotoSignup());
        panel3.add(signupButton);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        frame.setLocation(location);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public String getParam1() {
        return textFieldParam1.getText();
    }

    public String getParam2() {
        return textFieldParam2.getText();
    }

    public void showMessage(String message, int option){
        if( option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }
        if(option == 1){
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void dispose(){
        frame.dispose();
    }

    public Point getLocation(){
        return frame.getLocation();
    }
}
