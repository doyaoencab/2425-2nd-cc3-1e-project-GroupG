package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.views.LoginView;
import com.mycompany.employeemanagementsystemgui.views.RegisterView;
import com.mycompany.employeemanagementsystemgui.viewmodels.LoginViewModel;
import com.mycompany.employeemanagementsystemgui.controllers.DashboardController;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private LoginViewModel viewModel;

    public LoginController(LoginView view, LoginViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;

        // Action listener for the Login button
        view.getLoginButton().addActionListener(e -> {
            String username = view.getUsernameField().getText().trim();
            String password = new String(view.getPasswordField().getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (viewModel.authenticate(username, password)) {
                JOptionPane.showMessageDialog(null, "Login successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                
                // Open the Dashboard
                view.getFrame().dispose(); // Close the Login frame
                new DashboardController(username); // Pass the username to the Dashboard
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for the Register button
        view.getRegisterButton().addActionListener(e -> {
            // Open the RegisterView
            RegisterView registerView = new RegisterView();

            // Add action listener for "Register" button in RegisterView
            registerView.getRegisterButton().addActionListener(event -> {
                String username = registerView.getUsernameField().getText().trim();
                String password = new String(registerView.getPasswordField().getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView.getFrame(), "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (viewModel.register(username, password)) {
                    JOptionPane.showMessageDialog(registerView.getFrame(), "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    registerView.getFrame().dispose(); // Close the RegisterView frame
                } else {
                    JOptionPane.showMessageDialog(registerView.getFrame(), "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Add action listener for "Cancel" button in RegisterView
            registerView.getCancelButton().addActionListener(event -> registerView.getFrame().dispose());
        });

        // Action listener for the Forgot Password button
        view.getForgotPasswordButton().addActionListener(e -> {
            String username = JOptionPane.showInputDialog(view.getUsernameField(), "Enter your username:");

            if (username != null && !username.trim().isEmpty() && viewModel.recoverPassword(username.trim())) {
                String password = viewModel.getPassword(username.trim());
                JOptionPane.showMessageDialog(null, "Your password is: " + password, "Password Recovery", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        LoginView view = new LoginView();
        LoginViewModel viewModel = new LoginViewModel();
        new LoginController(view, viewModel);
    }
}
