package com.mycompany.employeemanagementsystemgui.models;

public class PayrollModel {
    private String employeeName;
    private double hoursWorked;
    private double hourlyRate;
    private double totalPay;

    public PayrollModel(String employeeName, double hoursWorked, double hourlyRate) {
        this.employeeName = employeeName;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.totalPay = hoursWorked * hourlyRate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getTotalPay() {
        return totalPay;
    }
}
