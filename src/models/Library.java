package models;

import models.book.*;
import models.console.*;
import models.member.MemberManager;

/**
 * This class represents a library, which manages books and members.
 * It provides functionalities to manage books, manage members, and borrow/return books.
 *
 * @author John
 */
public class Library {
    private static final MemberManager memberManager = new MemberManager();
    private static final BookManager bookManager = new BookManager();
    private static final String[] menuItems = new String[] {
            "Book Manager",
            "Member Manager",
            "Borrow/Return Book",
            "Exit"
    }; // Menu items

    /**
     * Starts the library management system.
     * Displays a menu to the user and performs actions based on the user's choice.
     */
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

    /**
     * Displays a menu for borrowing and returning books.
     * Prompts the user for the member ID and book ID and performs actions based on the user's choice.
     */
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

        for (Book book : bookManager.getBookList()) {
            if (book.getRemainAllowedTime() < 0) {
                book.setBorrowStatus(BorrowStatus.EXPIRED);
            }
        }

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


