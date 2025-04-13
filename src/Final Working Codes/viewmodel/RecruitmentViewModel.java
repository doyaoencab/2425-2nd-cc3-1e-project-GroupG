package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.employeemanagementsystemgui.models.RecruitmentModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class RecruitmentViewModel {
    private final String DATABASE_FILE = "recruitmentData.json";
    private List<RecruitmentModel> recruitmentList;

    public RecruitmentViewModel() {
        recruitmentList = loadRecruitmentData();
    }

    public List<RecruitmentModel> getRecruitmentList() {
        return recruitmentList;
    }

    public void addRecruitment(RecruitmentModel recruitment) {
        recruitmentList.add(recruitment);
        saveRecruitmentData();
    }

    public void editRecruitment(int index, RecruitmentModel updatedRecruitment) {
        recruitmentList.set(index, updatedRecruitment);
        saveRecruitmentData();
    }

    public void deleteRecruitment(int index) {
        recruitmentList.remove(index);
        saveRecruitmentData();
    }

    private List<RecruitmentModel> loadRecruitmentData() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            List<RecruitmentModel> data = gson.fromJson(reader, new TypeToken<List<RecruitmentModel>>() {}.getType());
            reader.close();
            return data != null ? data : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveRecruitmentData() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(DATABASE_FILE);
            gson.toJson(recruitmentList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
