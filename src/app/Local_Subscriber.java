package app;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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


    public ArrayList<String> loadFile(String filename) {
            //get data from Local
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
      
        if (filename.equals("Local.csv")) {
            //load the file
            ArrayList<String> data = loadFile(filename);
            //Save into Cloud'
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
