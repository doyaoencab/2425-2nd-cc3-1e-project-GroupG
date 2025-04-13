package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.employeemanagementsystemgui.models.LeaveModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LeaveViewModel {
    private final String DATABASE_FILE = "leaveHistory.json";
    private List<LeaveModel> leaveHistory;

    public LeaveViewModel() {
        leaveHistory = loadLeaveHistory();
    }

    public List<LeaveModel> getLeaveHistory() {
        return leaveHistory;
    }

    public void addLeave(LeaveModel leave) {
        leaveHistory.add(leave);
        saveLeaveHistory();
    }

    public void deleteLeave(int index) {
        leaveHistory.remove(index);
        saveLeaveHistory();
    }

    private List<LeaveModel> loadLeaveHistory() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            List<LeaveModel> history = gson.fromJson(reader, new TypeToken<List<LeaveModel>>() {}.getType());
            reader.close();
            return history != null ? history : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveLeaveHistory() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(leaveHistory, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
