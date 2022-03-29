package app;

public class NotificationManager {
    
    private NotificationManager() {
    }

    public static NotificationManager getInstance() {
        return new NotificationManager();
    }

    public void newBooking(String bookingDate){
        System.out.println("===== New Booking =====");
        System.out.println("Booking Date: " + bookingDate);
        
    }

    public void bookingCancelled(String bookingData){
        System.out.println("===== Booking Cancelled =====");
        System.out.println("Booking Date: " + bookingData);

    }
    public void connectionError(String arg){
        System.out.println("===== Connection Error =====");
        System.out.println("Error: " + arg);

    }

    public void systemStart(){
        System.out.println("===== System Started =====");

    }

    public void syncSuccessful(){
        System.out.println("===== Sync Successful =====");
        

    }


}
