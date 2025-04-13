package com.mycompany.employeemanagementsystemgui.models;

public class DashboardModel {
    private String loggedInUser;

    public DashboardModel(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}

