package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Abstract_Subscriber implements Subscriber {
    NotificationManager notifications = NotificationManager.getInstance();

    @Override
    public void update(String filename) {
        Settings settings = Settings.getInstance();
        NotificationManager notificationManager = NotificationManager.getInstance();

        if (settings.getUsername().equals("admin") && settings.getPassword().equals("password1")) {
            saveFile(filename);
        } else
            notificationManager.connectionError("Username and / or Password incorrect");
    }

    public ArrayList<String> loadFile(String filename) {
        // get data from Local
        ArrayList<String> data = new ArrayList<>();
        try {
            File LocalFile = new File(filename);
            Scanner myReader = new Scanner(LocalFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
            return data;

        } catch (FileNotFoundException e) {
            notifications.connectionError(String.valueOf(e));
            e.printStackTrace();
        }
        return data;
    }

    abstract public void saveFile(String filename);

}