package com.mycompany.metacustomer.Auth;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// this class is to handle Preferences in the project
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class HandlePreference {
    
    private final Preferences storage = Preferences.userRoot();
    
    public void saveToken(String token) {
        storage.put("Admin", token);
        System.out.println("save successful");
    }
    
    public String retrieveToken() {
        String token = storage.get("Admin", "not found");
        return token;
    }
    
    public void saveBaseurl(String url) {
        storage.put("baseurl", url);
        System.out.println("baseurl saved successfully");
    }
    
    public void saveAdminId(String adminId) {
        storage.put("adminId", adminId);
        System.out.println("adminId saved successfully.");
    }
    
    public String retrieveAdminId() {
        String id = storage.get("adminId", "not found");
        return id;
    }
    
    public String retrieveBaseurl() {
        String url = storage.get("baseurl", "not found");
        return url;
    }
    
    public boolean logout() {
        try {
            storage.remove("Admin");
            storage.remove("baseurl");
            storage.remove("adminId");
            return true;
        } catch (Exception ex) {
//            System.out.println("Failed to logout");
            return false;
        }
    }
    
}
