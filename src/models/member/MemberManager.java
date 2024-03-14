package models.member;

import models.book.*;
import models.console.Output;
import models.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManager extends Menu{
    private static ArrayList<Member> members; // Create an ArrayList to hold members
    private static final Scanner scan = new Scanner(System.in);

    public MemberManager() {
        members = new ArrayList<>(); // Initialize the ArrayList
        menuItems = new String[] {
            "Add Member",
            "Update Member",
            "Delete Member",
            "Display Member Details",
            "Search Member by Name",
            "Search Member by ID",
            "Display all Members",
            "Go back"
        };
    }

    // Method to start the member manager
    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            Output.printMenu("Member Manager", menuItems); // Display the menu
            int choice = scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    System.out.print("Enter member ID: ");
                    int memberID = scan.nextInt();
                    updateMember(memberID);
                    break;
                case 3:
                    System.out.print("Enter member ID: ");
                    memberID = scan.nextInt();
                    members.removeIf(member -> member.getMemberID() == memberID);
                    break;
                case 4:
                    System.out.print("Enter member ID: ");
                    memberID = scan.nextInt();
                    displayMemberDetails(memberID);
                    break;
                case 5:
                    System.out.print("Enter member name: ");
                    scan.nextLine(); // Consume the newline character
                    String name = scan.nextLine();
                    searchMemberByName(name);
                    break;
                case 6:
                    displayAllMembers();
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            Output.pause(scan);
            Output.clearScreen();
        }
    }

    // Method to create a member
    private void addMember() {
        System.out.print("Enter member name: ");
        scan.nextLine(); // Consume the newline character
        String name = scan.nextLine(); // Read the member name
        System.out.print("Enter member age (or press Enter to skip): ");
        String ageInput = scan.nextLine(); // Read the member age
        System.out.println("Enter member gender (M for Male, F for Female) (or press Enter to skip): ");
        String genderInput = scan.nextLine(); // Read the

        if (name.isEmpty()) {
            System.err.println("Name cannot be empty");
            return;
        }
        byte age = ageInput.isEmpty() ? 0 : Byte.parseByte(ageInput);
        char gender = genderInput.isEmpty() ? 'U' : genderInput.toUpperCase().charAt(0);

        Member member = new Member(name, age, gender); // Create a new member

        System.out.println("Member created successfully");
        System.out.println("Member ID: " + member.getMemberID());
        members.add(member);
    }

    // Method to update a member's details
    private void updateMember(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member
        
        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member.toString()); // Display the member details

        System.out.print("Enter member name (or press Enter to skip): ");
        String name = scan.nextLine(); // Read the member name
        System.out.println("Enter member age (or press Enter to skip): ");
        String ageInput = scan.nextLine(); // Read the member age

        if (!name.isEmpty()) {
            member.setName(name); // Update the member name
        }

        if (!ageInput.isEmpty()) {
            byte age = Byte.parseByte(ageInput);
            member.setAge(age); // Update the member age
        }
    }

    // Method to display a member's details
    private void displayMemberDetails(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member.toString()); // Display the member details
    }

    // Method to search a member by their name
    private void searchMemberByName(String name) {
        ArrayList<Member> searchResult = new ArrayList<>(); // Create an ArrayList to hold search results

        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResult.add(member); // Add the member to the search results
            }
        }

        if (searchResult.isEmpty()) {
            System.err.println("No members found with the given name");
        } else {
            System.out.println("Search results:");
            for (Member member : searchResult) {
                System.out.println(member.toString()); // Display the member details
            }
        }
    }

    // Method to display all members at once
    private void displayAllMembers() {
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

    // Method to search a member by their ID
    private Member searchMemberByID(int memberID) {
        // Initialize left and right pointers for binary search
        int left = 0;
        int right = members.size() - 1;
    
        // While the search space is not exhausted
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate the middle index
            Member member = members.get(mid); // Get the member at the middle index
    
            // If the member's ID matches the search ID, return the member
            if (member.getMemberID() == memberID) {
                return member;
            }
    
            // If the member's ID is less than the search ID, discard the left half
            if (member.getMemberID() < memberID) {
                left = mid + 1;
            } else {
                // Otherwise, discard the right half
                right = mid - 1;
            }
        }
    
        return null; // If the member is not found, return null
    }

    public void borrowOrReturn(BookManager bookManager) {
        String[] borrowMenuItems = new String[] {
                "Borrow a book",
                "Return a book",
                "Renew borrow time",
                "Go back"
        };

        Output.printMenu("Borrow/Return System", borrowMenuItems);
        int choice = scan.nextInt();
        Output.clearScreen();

        System.out.println("Enter member ID: ");
        int memberID = scan.nextInt();
        Member member = searchMemberByID(memberID);

        System.out.println("Enter book ID: ");
        int bookID = scan.nextInt();
        Book book = bookManager.searchBookByID(bookID);

        switch (choice) {
            case 1:
                if (!member.borrowBook(book)) {
                    System.err.println("This book is not available right now!!");
                } else {
                    System.out.println("The " + book.toString() + " is borrowed now.");
                }
                break;

            case 2:
                if (!member.returnBook(book)) {
                    System.err.println("This book is not borrowed by you!!");
                } else {
                    System.out.println("The " + book.toString() + " is returned now.");
                }
                break;

            // Other cases...
        }
    }
}
