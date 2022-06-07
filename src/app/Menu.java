package app;

import java.util.Scanner;

public class Menu {

    static Settings settings = Settings.getInstance();

    public void MenuScreen() {
        // show that the system has started

        Scanner scanner = new Scanner(System.in);
        int x = 1;
        // start the menu Loop
        while (x == 1) {
            System.out.println("----------------------------------------------------");
            System.out.println("0) Exit");
            System.out.println("1) Settings ");

            System.out.println("----------------------------------------------------");

            int input = scanner.nextInt();
            switch (input) {
                case 0:
                    System.out.println("Goodbye");
                    x = 0;
                    scanner.close();
                    break;
                case 1:

                    menuOptions(scanner);
                    break;

                default:
                    System.out.println("[!] Invalid input");
                    break;
            }
        }

    }

    private void menuOptions(Scanner scanner) {
        if (settings.login()) {
            menuText();
            int input1 = scanner.nextInt();
            switch (input1) {
                case 1:
                    System.out.println("Enter new username: ");

                    String newUsername = scanner.next();
                    settings.setUsername(newUsername);
                    settings.loadSettings();
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.next();
                    settings.setPassword(newPassword);
                    settings.loadSettings();
                    break;
                case 3:
                    System.out.println("Enter new admin password: ");
                    String newAdminPassword = scanner.next();
                    settings.setAdminPassword(newAdminPassword);
                    settings.loadSettings();
                    break;
                case 4:
                    System.out.println("Enter new spreadsheet location: ");
                    String newSpreadsheetLocation = scanner.next();
                    settings.setSpreadsheetLocation(newSpreadsheetLocation);
                    break;
                case 5:
                    settings.setNotification(!settings.getNotification());
                    break;

            }
        }
    }

    private void menuText() {
        System.out.println("===========================");
        System.out.println("1) Change Username");
        System.out.println("2) Change Password");
        System.out.println("3) Change Admin Password");
        System.out.println("4) Change Spreadsheet Location");
        System.out.println("5) " + settings.notificationReverseState() + " Notification");
        System.out.println("===========================");
    }

}
