package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.views.DashboardView;
import com.mycompany.employeemanagementsystemgui.viewmodels.DashboardViewModel;
import com.mycompany.employeemanagementsystemgui.controllers.EmployeeController;
import com.mycompany.employeemanagementsystemgui.controllers.LeaveController;
import com.mycompany.employeemanagementsystemgui.controllers.PayrollController;
import com.mycompany.employeemanagementsystemgui.controllers.RecruitmentController;
import com.mycompany.employeemanagementsystemgui.controllers.CalendarController;
import com.mycompany.employeemanagementsystemgui.controllers.LoginController;
import com.mycompany.employeemanagementsystemgui.views.LoginView;
import com.mycompany.employeemanagementsystemgui.viewmodels.LoginViewModel;

import javax.swing.*;

public class DashboardController {
    private DashboardView view;
    private DashboardViewModel viewModel;

    public DashboardController(String loggedInUser) {
        this.view = new DashboardView();
        this.viewModel = new DashboardViewModel(loggedInUser);

        // Set the welcome message
        JFrame frame = view.getFrame();
        frame.setTitle(viewModel.getWelcomeMessage());

        // Add button actions
        view.getEmployeeManagementButton().addActionListener(e -> {
            new EmployeeController(); // Open Employee Management
        });

        view.getLeaveManagementButton().addActionListener(e -> {
            new LeaveController(); // Open Leave Management
        });

        view.getPayrollManagementButton().addActionListener(e -> {
            new PayrollController(); // Open Payroll Management
        });

        view.getRecruitmentButton().addActionListener(e -> {
            new RecruitmentController(); // Open Recruitment Management
        });

        view.getCalendarButton().addActionListener(e -> {
            new CalendarController(); // Open Calendar Management
        });

        // Updated Logout action
        view.getLogoutButton().addActionListener(e -> {
            view.getFrame().dispose(); // Close the Dashboard frame
            new LoginController(new LoginView(), new LoginViewModel()); // Redirect to Login Frame
        });
    }

    public static void main(String[] args) {
        new DashboardController("Admin"); // Pass the logged-in user's name
    }
}
