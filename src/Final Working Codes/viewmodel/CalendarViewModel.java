package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.employeemanagementsystemgui.models.CalendarModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class CalendarViewModel {
    private final String DATABASE_FILE = "calendarEvents.json";
    private Map<String, String> events;

    public CalendarViewModel() {
        events = loadEvents();
    }

    public Map<String, String> getEvents() {
        return events;
    }

    public void addEvent(String date, String eventDetails) {
        events.put(date, eventDetails);
        saveEvents();
    }

    public String getEvent(String date) {
        return events.getOrDefault(date, null);
    }

    public void removeEvent(String date) {
        events.remove(date);
        saveEvents();
    }

    private Map<String, String> loadEvents() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            Map<String, String> data = gson.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType());
            reader.close();
            return data != null ? data : new HashMap<>();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveEvents() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(events, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
