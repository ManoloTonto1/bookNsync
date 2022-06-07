package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cloud_Subscriber extends Abstract_Subscriber {
    // access the settings
    NotificationManager notifications = NotificationManager.getInstance();
    static Local_Subscriber localhandler = Local_Subscriber.getInstance();
    static Cloud_Subscriber instance;

    private Cloud_Subscriber() {
        // load Username and Pasword.
    }

    public static Cloud_Subscriber getInstance() {
        if (instance == null) {
            instance = new Cloud_Subscriber();
        }
        return instance;
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
