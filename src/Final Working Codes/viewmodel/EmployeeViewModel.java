package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.employeemanagementsystemgui.models.EmployeeModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel {
    private final String DATABASE_FILE = "employeeData.json";
    private List<EmployeeModel> employeeList;

    public EmployeeViewModel() {
        employeeList = loadEmployeeData();
    }

    public List<EmployeeModel> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(EmployeeModel employee) {
        employeeList.add(employee);
        saveEmployeeData();
    }

    public void updateEmployee(int index, EmployeeModel updatedEmployee) {
        employeeList.set(index, updatedEmployee);
        saveEmployeeData();
    }

    public void deleteEmployee(int index) {
        employeeList.remove(index);
        saveEmployeeData();
    }

    private List<EmployeeModel> loadEmployeeData() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            List<EmployeeModel> employees = gson.fromJson(reader, new TypeToken<List<EmployeeModel>>() {}.getType());
            reader.close();
            return employees != null ? employees : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveEmployeeData() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(employeeList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
