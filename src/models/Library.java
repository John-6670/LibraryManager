package models;

import models.book.BookManager;
import models.console.Output;
import models.member.MemberManager;
import models.menu.Menu;

import java.util.Scanner;

public class Library extends Menu {
    private static final MemberManager memberManager = new MemberManager();
    private static final BookManager bookManager = new BookManager();
    private static final Scanner scan = new java.util.Scanner(System.in);

    public Library() {
        menuItems = new String[] {
            "Book Manager",
            "Member Manager",
            "Borrow/Return Book",
            "Exit"
        };
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            Output.printMenu("Library Management System", menuItems);
            int choice = scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    bookManager.start();
                    break;
                case 2:
                    memberManager.start();
                    break;
                case 3:
                    bookManager.displayAllBooks();
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
}
