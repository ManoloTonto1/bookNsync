package app;

import java.util.ArrayList;

public abstract class Abstract_Subscriber implements Subscriber {
    Settings settings = Settings.getInstance();
    NotificationManager notificationManager = NotificationManager.getInstance();

    @Override
    public void update(String filename) {
        if (settings.getUsername().equals("admin") && settings.getPassword().equals("password1")) {
            saveFile(filename);
        } else
            notificationManager.connectionError("Username and / or Password incorrect");
    }

    abstract public ArrayList<String> loadFile(String filename);

    abstract public void saveFile(String filename);

}