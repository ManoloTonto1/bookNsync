package app;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Local_Subscriber extends Abstract_Subscriber {
  // access the settings
  NotificationManager notifications = NotificationManager.getInstance();
  static Cloud_Subscriber cloudhandler = Cloud_Subscriber.getInstance();
  private static Local_Subscriber instance;

  Local_Subscriber() {
  }

  public static Local_Subscriber getInstance() {
    if (instance == null) {
      instance = new Local_Subscriber();
    }
    return instance;
  }

  public void saveFile(String filename) {

    if (filename.equals("Local.csv")) {
      // load the file
      ArrayList<String> data = loadFile(filename);
      // Save into Cloud'
      try {
        FileWriter myWriter = new FileWriter("Cloud.csv");
        for (String line : data) {
          myWriter.write(line);
          myWriter.write("\r\n");
        }

        myWriter.close();
        notifications.serverUpdate();
      } catch (IOException e) {
        notifications.connectionError(String.valueOf(e));
        e.printStackTrace();
      }

    }

  }

}
