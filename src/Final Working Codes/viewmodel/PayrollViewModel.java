package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.employeemanagementsystemgui.models.PayrollModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PayrollViewModel {
    private final String DATABASE_FILE = "payrollData.json";
    private List<PayrollModel> payrollList;

    public PayrollViewModel() {
        payrollList = loadPayrollData();
    }

    public List<PayrollModel> getPayrollList() {
        return payrollList;
    }

    public void addPayroll(PayrollModel payroll) {
        payrollList.add(payroll);
        savePayrollData();
    }

    public void deletePayroll(int index) {
        payrollList.remove(index);
        savePayrollData();
    }

    private List<PayrollModel> loadPayrollData() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            List<PayrollModel> data = gson.fromJson(reader, new TypeToken<List<PayrollModel>>() {}.getType());
            reader.close();
            return data != null ? data : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void savePayrollData() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(payrollList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
