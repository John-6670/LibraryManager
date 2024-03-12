package models;

import models.book.BookManager;
import models.console.Output;
import models.member.MemberManager;
public class Library {
    public static final MemberManager memberManager = new MemberManager();
    public static final BookManager bookManager = new BookManager();

    public void start(java.util.Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("          Library Management System");
            System.out.println("------------------------------------------------");
            System.out.println("1. Book Manager");
            System.out.println("2. Member Manager");
            System.out.println("3. Borrow/Return Book");
            System.out.println("4. Display all members");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookManager.start(scanner);
                    break;
                case 2:
                    // memberManager.start(scanner);
                    break;
                case 3:
                    bookManager.displayAllBooks();
                    break;
                case 4:
                    memberManager.displayAllMembers();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
