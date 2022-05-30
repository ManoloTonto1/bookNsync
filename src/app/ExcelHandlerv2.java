package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Scanner;


public class ExcelHandlerv2 {
    // access the settings
    NotificationManager notifications = NotificationManager.getInstance();
    static ExcelHandlerv2 instance;
    public String type = "excel";

    ExcelHandlerv2() {
    }

    public static ExcelHandlerv2 getInstance() {
        if (instance == null) {
            instance = new ExcelHandlerv2();
        }
        return instance;
    }

    public void Listner(){
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run(){
                try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                    Path path = Paths.get(System.getProperty("user.dir"));
      
                    try {
                        path.register(
                                watchService,
                                StandardWatchEventKinds.ENTRY_CREATE,
                                StandardWatchEventKinds.ENTRY_DELETE,
                                StandardWatchEventKinds.ENTRY_MODIFY);
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
      
                    WatchKey key;
                    while ((key = watchService.take()) != null) {
                        for (WatchEvent<?> event : key.pollEvents()) {
                            String file = String.valueOf(event.context());
                            saveFile(file, "files");
                        }
                        key.reset();
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        t1.start();
        
    }

    public Boolean writeToFile(Boolean fileIsloaded, String cel, String input) {
        int CellLength = String.valueOf(cel).length();
        if (CellLength >= 3 && !input.equals("") && fileIsloaded) {
            return true;
        }
        return false;
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
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            return data;
    }

    public String saveFile(String filename, String dir) {
        if (filename.equals("Local.csv") && dir == "files") {
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
                System.out.println("Successfully wrote to the file.");
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }

            String toReturn = "/" + dir + "/" + filename;
            return toReturn;
        }
        return "error";
    }
}
