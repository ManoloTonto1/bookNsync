package app;

public class NotificationManager {
    
    private NotificationManager() {
    }

    public static NotificationManager getInstance() {
        return new NotificationManager();
    }

    public void serverUpdate(){
        System.out.println("===== Successfully Updated Server =====");
        
    }

    public void localUpdate(){
        System.out.println("===== Successfully Updated Local File =====");
        

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
