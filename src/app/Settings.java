package app;

import java.util.Scanner;

public class Settings {
    static JsonHandler json = JsonHandler.getInstance();
    private static Settings instance;
    private String username;
    private String password;
    private String adminPassword;
    private String spreadsheetLocation;
    private Boolean enableNotification;
    private Boolean isLogged;
    

    private Settings() {
        Properties[] loadedSettings = json.getSettigs();
        this.username = loadedSettings[0].getText();
        this.password = loadedSettings[1].getText();
        this.adminPassword = loadedSettings[2].getText();
        this.spreadsheetLocation = loadedSettings[3].getText();
        this.enableNotification = loadedSettings[4].getBool();
        this.isLogged = false;
    

    }
    // // Used for settings
    // private Settings() {
    //     this.username = "username";
    //     this.password = "password";
    //     this.adminPassword = "bomama";
    //     this.spreadsheetLocation = "spreadsheetLocation";
    //     this.enableNotification = true;
    //     this.isLogged = false;

    // }

//Constructor
    public static Settings getInstance() {
        
        
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
        
    }
    public static Settings getInstanceTest() {
        
        //Properties[] loadedSettings = json.getSettigs();
        return new Settings();
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
    public Boolean login(String input){
        //System.out.println(isLogged);
        if(this.isLogged) return true;
        
        System.out.println("Password: ");
        String password = input;
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
