package app;

import java.io.IOException;

import org.apache.poi.hssf.record.BookBoolRecord;

public class Main{
    static Menu menu = new Menu();
    static ExcelHandlerv2 x = new ExcelHandlerv2();
    static BookingHandler y = BookingHandler.getInstance();
    //static Sync sync = new Sync();

    //start the sync progam

    public static void main(String[] args) throws IOException, InterruptedException {
        y.Listner();
        x.Listner();
        menu.MenuScreen();

    }
} 
