package view;

import controller.SignupController;

import javax.swing.*;
import java.awt.*;

public class SignupView extends JPanel{
        JFrame frame = new JFrame("SignUp");
        SignupController controller = new SignupController(this);

        JPanel panel, panel1, panel2, panel3;

        JLabel labelParam1, labelParam2;
        JTextField textFieldParam1, textFieldParam2;
        JButton addButton, loginButton;

        public SignupView(Point location) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 600);
                frame.setResizable(false);



                panel1 = new JPanel();
                panel2 = new JPanel();
                panel3 = new JPanel();

                labelParam1 = new JLabel("Username: ");
                textFieldParam1 = new JTextField();
                textFieldParam1.setPreferredSize(new Dimension(100, 20));
                panel1.add(labelParam1);
                panel1.add(textFieldParam1);
                labelParam2 = new JLabel("Password");
                textFieldParam2 = new JTextField();
                textFieldParam2.setPreferredSize(new Dimension(100, 20));
                panel1.add(labelParam2);
                panel1.add(textFieldParam2);

                addButton = new JButton("Create Account");
                addButton.addActionListener(e -> this.controller.addUser());
                panel2.add(addButton);

                loginButton = new JButton("Go to Login");
                loginButton.addActionListener(e -> this.controller.changeView());
                panel3.add(loginButton);

                panel = new JPanel();
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
