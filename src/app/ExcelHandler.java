package app;

import java.io.FileWriter;
import java.io.IOException;

public class ExcelHandler implements Subscriber {
    // access the settings
    Settings settings = Settings.getInstance();
    public String type = "excel";
    private String filepath = settings.getSpreadsheetLocation();

    ExcelHandler() {
    }

    public static ExcelHandler getInstance() {
        return new ExcelHandler();
    }

    @Override
    public void update(String context, String content) {
        // TODO Auto-generated method stub
        // write to excel logic
        // its suppoesed to be more complex by using the POI library
        try {
            FileWriter file = new FileWriter(this.filepath);
            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
