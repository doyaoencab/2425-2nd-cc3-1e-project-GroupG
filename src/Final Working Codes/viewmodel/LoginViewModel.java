package com.mycompany.employeemanagementsystemgui.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoginViewModel {
    private final String DATABASE_FILE = "userData.json";
    private Map<String, String> userDatabase;

    public LoginViewModel() {
        userDatabase = loadDatabase();
    }

    public boolean authenticate(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public boolean register(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return false; // Username already exists
        }
        userDatabase.put(username, password);
        saveDatabase();
        return true;
    }

    public boolean recoverPassword(String username) {
        return userDatabase.containsKey(username);
    }

    public String getPassword(String username) {
        return userDatabase.get(username);
    }

    private Map<String, String> loadDatabase() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATABASE_FILE);
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveDatabase() {
        try (FileWriter writer = new FileWriter(DATABASE_FILE)) {
            new Gson().toJson(userDatabase, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
