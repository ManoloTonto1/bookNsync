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

public class BookingHandler implements Subscriber {
    // access the settings
    Settings settings = Settings.getInstance();
    NotificationManager notifications = NotificationManager.getInstance();

    public String type = "booking";
    private String username = settings.getUsername();
    private String password = settings.getPassword();
    static BookingHandler instance;

    private BookingHandler() {
    }

    public static BookingHandler getInstance() {
        if (instance == null) {
            instance = new BookingHandler();
        }
        return instance;
    }

    public void Listner() throws IOException, InterruptedException {
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
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
                    try {
                        while ((key = watchService.take()) != null) {
                            for (WatchEvent<?> event : key.pollEvents()) {
                                String file = String.valueOf(event.context());
                                saveFile(file, "server");
                            }
                            key.reset();
                        }
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }

            }
        });
        t2.start();

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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public String saveFile(String filename, String dir) {
        if (filename.equals("Cloud.csv") && dir.equals("server")) {
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

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            String toReturn = "/" + dir + "/" + filename;
            return toReturn;
        }
        return "error";
    }

    @Override
    public void update(String context, String content) {

    }

}
