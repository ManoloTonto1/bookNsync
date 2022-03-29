package app;

import java.util.ArrayList;

//import org.apache.poi.hpsf.Array;

public class Sync {
    private Subscriber[] subscribers;
    ArrayList<Object> filechanges = new ArrayList<>();

    public Sync() {
        subscribers = new Subscriber[2];
        subscribers[0] = BookingHandler.getInstance();
        subscribers[1] = ExcelHandler.getInstance();

    }
    //send information to all subscribers
    public void notifySubscribers(String context, String content) {
        for (Subscriber subscriber : subscribers) {
            //check if the subscriber is booking or excel
            if (Subscriber.type.equals(context)) {
                subscriber.update(context, content);
            }
        }
    }

}
