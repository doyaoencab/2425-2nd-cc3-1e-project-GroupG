package com.employeemanagementsystem;

import java.util.Date;

class Employee {
    private int employeeID;
    private String name;
    private int age; 
    private String department;
    private String position;
    private double salary;
    private Date hiredDate;
    private int leaveBalance;

    public Employee(int employeeID, String name, int age, String department, String position, double salary, Date hiredDate, int leaveBalance) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hiredDate = hiredDate;
        this.leaveBalance = leaveBalance;
    }

    //Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
}
