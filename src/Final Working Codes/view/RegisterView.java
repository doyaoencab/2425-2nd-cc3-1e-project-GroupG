package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import java.awt.*;

public class RegisterView {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, cancelButton;

    public RegisterView() {
        frame = new JFrame("Register");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Register a New Account");
        title.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        grid.gridx = 0; grid.gridy = 0; grid.gridwidth = 2;
        panel.add(title, grid);

        grid.gridwidth = 1;
        grid.gridx = 0; grid.gridy = 1;
        panel.add(usernameLabel, grid);
        grid.gridx = 1;
        panel.add(usernameField, grid);

        grid.gridx = 0; grid.gridy = 2;
        panel.add(passwordLabel, grid);
        grid.gridx = 1;
        panel.add(passwordField, grid);

        grid.gridx = 0; grid.gridy = 3; grid.gridwidth = 2;
        panel.add(registerButton, grid);

        grid.gridy = 4;
        panel.add(cancelButton, grid);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
