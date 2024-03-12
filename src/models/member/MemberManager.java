package models.member;

import java.util.ArrayList;
import java.util.Scanner;

import models.console.Output;
import models.menu.Menu;

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
                    String memberID = scan.next();
                    try {
                        updateMember(Integer.parseInt(memberID));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid member ID");
                    }
                    break;
                case 3:
                    System.out.print("Enter member ID: ");
                    memberID = scan.next();
                    try {
                        members.removeIf(member -> member.getMemberID() == Integer.parseInt(memberID));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid member ID");
                    }
                    break;
                case 4:
                    System.out.print("Enter member ID: ");
                    memberID = scan.next();
                    try {
                        displayMemberDetails(Integer.parseInt(memberID));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid member ID");
                    }
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

            Output.pause();
            Output.clearScreen();
        }
    }

    // Method to create a member
    public void addMember() {
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
    public void updateMember(int memberID) {
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
    public void displayMemberDetails(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member.toString()); // Display the member details
    }

    // Method to search a member by their name
    public void searchMemberByName(String name) {
        ArrayList<Member> searchResults = new ArrayList<>(); // Create an ArrayList to hold search results

        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(member); // Add the member to the search results
            }
        }

        if (searchResults.isEmpty()) {
            System.err.println("No members found with the given name");
        } else {
            System.out.println("Search results:");
            for (Member member : searchResults) {
                System.out.println(member.toString() + "\nMember ID: " + member.getMemberID()); // Display the member details
            }
        }
    }

    // Method to display all members at once
    public void displayAllMembers() {
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
}
