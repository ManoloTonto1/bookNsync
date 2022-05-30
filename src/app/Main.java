package app;

import java.io.IOException;

public class Main{
    static Menu menu = new Menu();
    static ExcelHandlerv2 x = new ExcelHandlerv2();
    //static Sync sync = new Sync();

    //start the sync progam

    public static void main(String[] args) throws IOException, InterruptedException {
        
        x.Listner();
        menu.MenuScreen();

    }
} 
