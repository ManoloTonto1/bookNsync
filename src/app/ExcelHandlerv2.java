package app;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class ExcelHandlerv2 {
    // access the settings
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

    public void Listner() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(System.getProperty("user.dir"));

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

    public Boolean writeToFile(Boolean fileIsloaded, String cel, String input) {
        int CellLength = String.valueOf(cel).length();
        if (CellLength >= 3 && !input.equals("") && fileIsloaded) {
            return true;
        }
        return false;
    }

    public Boolean loadFile(String filename) {
        if (filename == "test.txt")
            return true;
        return false;
    }

    public String saveFile(String filename, String dir) {
        if (filename == "test.txt" && dir == "files") {
            String toReturn = "/" + dir + "/" + filename;
            return toReturn;
        }
        return "error";
    }
}
