package com.mycompany.employeemanagementsystemgui.models;

public class CalendarModel {
    private String date; // e.g., "day-month"
    private String eventDetails;

    public CalendarModel(String date, String eventDetails) {
        this.date = date;
        this.eventDetails = eventDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
