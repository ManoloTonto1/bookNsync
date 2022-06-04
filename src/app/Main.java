package app;

import java.io.IOException;


public class Main{
    static Menu menu = new Menu();
    static Sync_Provider provider = new Sync_Provider();
    static Local_Subscriber local = Local_Subscriber.getInstance();
    static Cloud_Subscriber cloud = Cloud_Subscriber.getInstance();

    //start the sync progam

    public static void main(String[] args) throws IOException, InterruptedException {
        provider.Subscribe(local);
        provider.Subscribe(cloud);
        provider.Sync();
        menu.MenuScreen();

    }
} 
