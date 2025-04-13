package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.mycompany.employeemanagementsystemgui.models.DashboardModel;

public class DashboardViewModel {
    private DashboardModel model;

    public DashboardViewModel(String loggedInUser) {
        this.model = new DashboardModel(loggedInUser);
    }

    public String getWelcomeMessage() {
        return "Welcome, " + model.getLoggedInUser() + "!";
    }
}
