package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, forgotPasswordButton;

    public LoginView() {
        frame = new JFrame("Employee Management System");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.insets = new Insets(10, 10, 10, 10);
        grid.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        forgotPasswordButton = new JButton("Forgot Password?");

        grid.gridx = 0; grid.gridy = 0; grid.gridwidth = 2;
        panel.add(title, grid);

        grid.gridwidth = 1;
        grid.gridx = 0; grid.gridy = 1; grid.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, grid);
        grid.gridx = 1; grid.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, grid);

        grid.gridx = 0; grid.gridy = 2; grid.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, grid);
        grid.gridx = 1; grid.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, grid);

        grid.gridwidth = 2; grid.gridx = 0; grid.gridy = 3; grid.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, grid);

        grid.gridy = 4;
        panel.add(registerButton, grid);

        grid.gridy = 5;
        panel.add(forgotPasswordButton, grid);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
