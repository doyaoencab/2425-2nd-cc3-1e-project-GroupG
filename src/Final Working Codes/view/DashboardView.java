package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import java.awt.*;

public class DashboardView {
    private JFrame frame;
    private JPanel panel;
    private JButton employeeManagementButton, leaveManagementButton, payrollManagementButton, recruitmentButton, calendarButton, logoutButton;

    public DashboardView() {
        frame = new JFrame("Dashboard");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.insets = new Insets(10, 10, 10, 10);
        grid.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Welcome to Employee Management System!", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        grid.gridx = 0; grid.gridy = 0; grid.gridwidth = 2;
        panel.add(title, grid);

        employeeManagementButton = new JButton("Employee Management");
        leaveManagementButton = new JButton("Leave Management");
        payrollManagementButton = new JButton("Payroll Management");
        recruitmentButton = new JButton("Recruitment");
        calendarButton = new JButton("Calendar");
        logoutButton = new JButton("Logout");

        Font buttonFont = new Font("SansSerif", Font.BOLD, 12);
        employeeManagementButton.setFont(buttonFont);
        leaveManagementButton.setFont(buttonFont);
        payrollManagementButton.setFont(buttonFont);
        recruitmentButton.setFont(buttonFont);
        calendarButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);

        grid.gridy = 1; grid.gridwidth = 2;
        panel.add(employeeManagementButton, grid);

        grid.gridy = 2;
        panel.add(leaveManagementButton, grid);

        grid.gridy = 3;
        panel.add(payrollManagementButton, grid);

        grid.gridy = 4;
        panel.add(recruitmentButton, grid);

        grid.gridy = 5;
        panel.add(calendarButton, grid);

        grid.gridy = 6; // Place Logout button at the bottom
        panel.add(logoutButton, grid);

        setButtonSizes(employeeManagementButton, leaveManagementButton, payrollManagementButton, recruitmentButton, calendarButton, logoutButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setButtonSizes(JButton... buttons) {
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(200, 30));
            button.setMinimumSize(new Dimension(200, 30));
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getEmployeeManagementButton() {
        return employeeManagementButton;
    }

    public JButton getLeaveManagementButton() {
        return leaveManagementButton;
    }

    public JButton getPayrollManagementButton() {
        return payrollManagementButton;
    }

    public JButton getRecruitmentButton() {
        return recruitmentButton;
    }

    public JButton getCalendarButton() {
        return calendarButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
