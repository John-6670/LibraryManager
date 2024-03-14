package models;

import models.book.*;
import models.console.*;
import models.member.*;

public class Library {
    private static final MemberManager memberManager = new MemberManager();
    private static final BookManager bookManager = new BookManager();
    private static final String[] menuItems = new String[] {
            "Book Manager",
            "Member Manager",
            "Borrow/Return Book",
            "Exit"
    }; // Menu items

    public static void start() {
        boolean isRunning = true;
        while (isRunning) {
            Output.printMenu("Library Management System", menuItems);
            int choice = Input.scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    bookManager.start();
                    break;
                case 2:
                    memberManager.start();
                    break;
                case 3:
                    borrowReturnMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void borrowReturnMenu() {
        String[] borrowMenuItems = new String[] {
                "Borrow a book",
                "Return a book",
                "Go back"
        };

        Output.printMenu("Borrow/Return System", borrowMenuItems);
        int choice = Input.scan.nextInt();
        Output.clearScreen();

        System.out.println("Enter member ID: ");
        int memberID = Input.scan.nextInt();
        System.out.println("Enter book ID: ");
        int bookID = Input.scan.nextInt();

        switch (choice) {
            case 1:
                memberManager.borrowBook(bookManager, memberID, bookID);
                break;

            case 2:
                memberManager.returnBook(bookManager, memberID, bookID);
                break;

            case 3:
                // Go back
                break;

            default:
                System.out.println("Invalid choice");
        }
    }
}
