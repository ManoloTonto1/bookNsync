package app;

import java.util.Scanner;

public class Settings {
    static JsonHandler json = JsonHandler.getInstance();
    private String username;
    private String password;
    private String adminPassword;
    private String spreadsheetLocation;
    private Boolean enableNotification;
    private Boolean isLogged;
    

    private Settings(Properties[] loadedSettings) {
        this.username = loadedSettings[0].getText();
        this.password = loadedSettings[1].getText();
        this.adminPassword = loadedSettings[2].getText();
        this.spreadsheetLocation = loadedSettings[3].getText();
        this.enableNotification = loadedSettings[4].getBool();
        this.isLogged = false;

    }

//Constructor
    public static Settings getInstance() {
        
        Properties[] loadedSettings = json.getSettigs();
        return new Settings(loadedSettings);
    }
//functions

    public Boolean login(){
        //System.out.println(isLogged);
        if(this.isLogged) return true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Password: ");
        String password = scanner.nextLine();
        if (password.equals(this.adminPassword)) {
            System.out.println("Login successful");
            this.isLogged = true;
            return true;
        } else {
            System.out.println("Login failed");
            return false;
        }
    }
    //check if the user is logged in
    public Boolean isLoggedin(){
        return this.isLogged;
    }

    public String notificationReverseState(){
        if (this.enableNotification == true) return "Disable";
        else return "Enable";
    }
        
    
    //Getters
    public String getAdminPassword() {
        return adminPassword;
    }


    public String getPassword() {
        return password;
    }

    public String getSpreadsheetLocation() {
        return spreadsheetLocation;
    }

    public String getUsername() {
        return username;
    }
    public Boolean getNotification() {
        return enableNotification;
    }


    //Setters
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        json.setSettings(this);
        System.out.println("Admin password successfully changed");
    }

    public void setNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
        json.setSettings(this);
        System.out.println("Notification settings successfully changed");
    }

    public void setPassword(String password) {
        this.password = password;
        json.setSettings(this);
        System.out.println("Password successfully changed");
    }

    public void setSpreadsheetLocation(String spreadsheetLocation) {
        this.spreadsheetLocation = spreadsheetLocation;
        json.setSettings(this);
        System.out.println("Spreadsheet location successfully changed");
        
    }

    public void setUsername(String username) {
        this.username = username;
        json.setSettings(this);
        System.out.println("Username successfully changed");
    }

}

// apply polymorphism
class Properties {
    private String text;
    private Boolean bool;

    public String getText() {
        return text;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public void setText(String text) {
        this.text = text;
    }

}

class Text extends Properties {

    public Text(String text) {
        super.setText(text);
    }

}

class Bool extends Properties {

    public Bool(Boolean bool) {
        super.setBool(bool);
    }

}
