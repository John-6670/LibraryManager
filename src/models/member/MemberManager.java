package models.member;

import models.book.*;
import models.console.*;
import models.base.Manager;

import java.util.ArrayList;

/**
 * This class manages the members of the library.
 * It provides functionalities to add, update, delete, display and search members.
 * It also handles the borrowing and returning of books by members.
 *
 * @author John
 */
public class MemberManager implements Manager{
    private final ArrayList<Member> members; // Create an ArrayList to hold members
    private static final String[] menuItems = new String[] {
            "Add Member",
            "Display all Members",
            "Update Member",
            "Search Member by Name",
            "Display Member Details",
            "Delete Member",
            "Go back"
    }; // Menu items

    /**
     * Constructor for MemberManager class.
     * Initializes the ArrayList of members.
     */
    public MemberManager() {
        members = new ArrayList<>(); // Initialize the ArrayList
    }

    /**
     * Starts the member manager.
     * Displays a menu to the user and performs actions based on the user's choice.
     */
    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            Output.printMenu("Member Manager", menuItems); // Display the menu
            int choice = Input.scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    add();
                    break;

                case 2:
                    list();
                    break;

                case 3:
                    System.out.print("Enter member ID: ");
                    int memberID = Input.scan.nextInt();
                    update(memberID);
                    break;

                case 4:
                    System.out.print("Enter member name: ");
                    Input.scan.nextLine(); // Consume the newline character
                    String name = Input.scan.nextLine();
                    search(name);
                    break;

                case 5:
                    System.out.print("Enter member ID: ");
                    memberID = Input.scan.nextInt();
                    displayMemberDetails(memberID);
                    break;

                case 6:
                    System.out.print("Enter member ID: ");
                    memberID = Input.scan.nextInt();
                    delete(memberID);
                    break;

                case 7:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            if (isRunning) {
                Output.pause(Input.scan);
            }
            Output.clearScreen();
        }
    }

    /**
     * Adds a new member to the library.
     * Prompts the user for the member's details and creates a new Member object.
     */
    @Override
    public void add() {
        System.out.print("Enter member name: ");
        Input.scan.nextLine(); // Consume the newline character
        String name = Input.scan.nextLine(); // Read the member name
        name = name.toUpperCase().charAt(0) + name.substring(1).toLowerCase(); // Capitalize the first letter

        System.out.print("Enter member age (or press Enter to skip): ");
        String ageInput = Input.scan.nextLine(); // Read the member age

        System.out.println("Enter member gender (M for Male, F for Female) (or press Enter to skip): ");
        String genderInput = Input.scan.nextLine(); // Read the

        Byte age = ageInput.isEmpty() ? null : Byte.parseByte(ageInput);
        Character gender = genderInput.isEmpty() ? null : genderInput.toUpperCase().charAt(0);

        Member member = new Member(name, age, gender); // Create a new member

        System.out.println("Member created successfully");
        System.out.println("Member ID: " + member.getID());
        members.add(member);
    }

    /**
     * Displays the details of all members.
     */
    @Override
    public void list() {
        System.out.printf ("| %-9s | %-18s | %-7s | %-7s | %-24s |\n", "Member ID", "Name", "Age", "Gender", "Number of borrowed books");
        System.out.println("|-----------|--------------------|---------|---------|--------------------------|");

        for (Member member : members) {
            System.out.println(member.toStringTable());
        }
    }

    /**
     * Updates a member's details.
     * Prompts the user for the new details and updates the Member object.
     *
     * @param memberID The ID of the member to be updated.
     */
    @Override
    public void update(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member
        
        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member); // Display the member details

        System.out.print("Enter member name (or press Enter to skip): ");
        String name = Input.scan.nextLine(); // Read the member name
        System.out.println("Enter member age (or press Enter to skip): ");
        String ageInput = Input.scan.nextLine(); // Read the member age

        if (!name.isEmpty()) {
            member.setName(name); // Update the member name
        }

        if (!ageInput.isEmpty()) {
            byte age = Byte.parseByte(ageInput);
            member.setAge(age); // Update the member age
        }
    }

    /**
     * Searches for a member by their name.
     * Prints the details of all members whose names contain the given name.
     *
     * @param name The name to search for.
     */
    @Override
    public void search(String name) {
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

    /**
     * Deletes a member from the library.
     * Searches for the member by their ID and removes them from the list of members.
     *
     * @param memberID The ID of the member to be deleted.
     */
    @Override
    public void delete(int memberID) {
        members.removeIf(member -> member.getID() == memberID); // Remove the member from the list
    }

    /**
     * Displays a member's details.
     * Searches for the member by their ID and prints their details.
     *
     * @param memberID The ID of the member whose details are to be displayed.
     */
    private void displayMemberDetails(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member); // Display the member details
    }

    /**
     * Searches for a member by their ID.
     * Uses binary search to find the member.
     *
     * @param memberID The ID to search for.
     *
     * @return The Member object if found, null otherwise.
     */
    private Member searchMemberByID(int memberID) {
        // Initialize left and right pointers for binary search
        int left = 0;
        int right = members.size() - 1;
    
        // While the search space is not exhausted
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate the middle index
            Member member = members.get(mid); // Get the member at the middle index
    
            // If the member's ID matches the search ID, return the member
            if (member.getID() == memberID) {
                return member;
            }
    
            // If the member's ID is less than the search ID, discard the left half
            if (member.getID() < memberID) {
                left = mid + 1;
            } else {
                // Otherwise, discard the right half
                right = mid - 1;
            }
        }
    
        return null; // If the member is not found, return null
    }

    /**
     * Borrows a book for a member.
     * Searches for the member and the book by their IDs and adds the book to the member's borrowed books.
     *
     * @param bookManager The BookManager object to search for the book.
     * @param memberID The ID of the member who wants to borrow the book.
     * @param bookID The ID of the book to be borrowed.
     */
    public void borrowBook(BookManager bookManager, int memberID, int bookID) {
        Member member = searchMemberByID(memberID); // Search for the member
        Book book = bookManager.searchByID(bookID); // Search for the book

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        if (book == null) {
            System.err.println("Book not found");
            return;
        }

        if (member.getBorrowedBooks().size() >= Member.MAX_BORROWED_BOOKS) {
            System.err.println("Member has already borrowed 5 books");
            return;
        }

        // Add the book to the member's borrowed books
        if (!member.borrowBook(book)) {
            System.out.println("Book is not available for borrowing or member has expired borrow time limit");
            return;
        }

        System.out.println("Book borrowed successfully");
    }

    /**
     * Returns a book borrowed by a member.
     * Searches for the member and the book by their IDs and removes the book from the member's borrowed books.
     *
     * @param bookManager The BookManager object to search for the book.
     * @param memberID The ID of the member who wants to return the book.
     * @param bookID The ID of the book to be returned.
     */
    public void returnBook(BookManager bookManager, int memberID, int bookID) {
        Member member = searchMemberByID(memberID); // Search for the member
        Book book = bookManager.searchByID(bookID); // Search for the book

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        if (book == null) {
            System.err.println("Book not found");
            return;
        }

        // Remove the book from the member's borrowed books
        if (!member.returnBook(book)) {
            System.out.println("Book is not borrowed by the member");
            return;
        }
        System.out.println("Book returned successfully");
    }
}
