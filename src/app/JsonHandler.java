package app;

//imoprt java natives
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
//import JSON
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonHandler {

    private JsonHandler() {
    }

    public static JsonHandler getInstance() {
        return new JsonHandler();
    }

    // @SuppressWarnings("unchecked")
    public Properties[] getSettigs() {
        Properties[] settings = new Properties[5];
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("appsettings.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //Get all the data from the Json file
            String username = (String) jsonObject.get("name");
            String Password = (String) jsonObject.get("password");
            String adminPassword = (String) jsonObject.get("adminPassword");
            String spreadsheetLocation = (String) jsonObject.get("spreadsheetLocation");
            Boolean enableNotification = (Boolean) jsonObject.get("notifications");
            //Add it to the array
            settings[0] = new Text(username);
            settings[1] = new Text(Password);
            settings[2] = new Text(adminPassword);
            settings[3] = new Text(spreadsheetLocation);
            settings[4] = new Bool(enableNotification);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Read info from the json

        return settings;
    }

    @SuppressWarnings("unchecked")
    public void setSettings(Settings settings) {
        JSONObject obj = new JSONObject();
        obj.put("username", settings.getUsername());
        obj.put("password", settings.getPassword());
        obj.put("adminPassword", settings.getAdminPassword());
        obj.put("spreadsheetLocation", settings.getSpreadsheetLocation());
        obj.put("notifications", settings.getNotification());

        try (FileWriter file = new FileWriter("appsettings.json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

}
