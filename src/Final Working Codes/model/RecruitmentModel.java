package com.mycompany.employeemanagementsystemgui.models;

public class RecruitmentModel {
    private String employeeName;
    private String position;
    private String interviewDate;
    private String status;

    public RecruitmentModel(String employeeName, String position, String interviewDate, String status) {
        this.employeeName = employeeName;
        this.position = position;
        this.interviewDate = interviewDate;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
