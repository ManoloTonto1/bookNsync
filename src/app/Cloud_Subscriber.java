package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cloud_Subscriber extends Abstract_Subscriber {
    // access the settings
    NotificationManager notifications = NotificationManager.getInstance();
    static Local_Subscriber localhandler = Local_Subscriber.getInstance();
    static Cloud_Subscriber instance;

    private Cloud_Subscriber() {
        //load Username and Pasword.
    }

    public static Cloud_Subscriber getInstance() {
        if (instance == null) {
            instance = new Cloud_Subscriber();
        }
        return instance;
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

    public void saveFile(String filename) {
        if (filename.equals("Cloud.csv")) {
            // load the file
            ArrayList<String> data = loadFile(filename);
            // Save into Cloud'
            try {
                FileWriter myWriter = new FileWriter("Local.csv");
                for (String line : data) {
                    myWriter.write(line);
                    myWriter.write("\r\n");
                }

                myWriter.close();
                notifications.localUpdate();
            } catch (IOException e) {
                notifications.connectionError(String.valueOf(e));
                e.printStackTrace();
            }

        }

    }

}
