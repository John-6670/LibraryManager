package models;

import models.book.*;
import models.console.Output;
import models.member.*;
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
                    borrowOrReturn();
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

    private void borrowOrReturn() {
        String[] borrowMenuItems = new String[] {
                "Borrow a book",
                "Return a book",
                "Go back"
        };

        Output.printMenu("Borrow/Return System", borrowMenuItems);
        int choice = scan.nextInt();
        Output.clearScreen();

        System.out.println("Enter member ID: ");
        int memberID = scan.nextInt();
        Member member = memberManager.searchMemberByID(memberID);

        System.out.println("Enter book ID: ");
        int bookID = scan.nextInt();
        Book book = bookManager.searchBookByID(bookID);

        switch (choice) {
            case 1:
                if (!borrowBook(member, book)) {
                    System.err.println("This book is not available right now!!");
                } else {
                    System.out.println("The " + book.toString() + " is borrowed now.");
                }
                break;

        }
    }

    private boolean borrowBook(Member member, Book book) {
        if (book.getBorrower() == null || book.getBorrowStatus() != BorrowStatus.AVAILABLE) {
            return false;
        }

        member.borrowBook(book);
        return true;
    }
}
