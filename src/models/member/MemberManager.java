package models.member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManager {
    private static ArrayList<Member> members; // Create an ArrayList to hold members
    private static final Scanner scan = new Scanner(System.in);

    // Constructor
    public MemberManager() {
        members = new ArrayList<>();
    }

    // Method to create a member
    public void addMember(String name, Byte age, Character gender) {
        Member member = new Member(name, age, gender);

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

        System.out.println(member.getMemberDetails()); // Display the member details

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

    // Method to delete a member
    public void deleteMember(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        members.remove(member); // Remove the member from the ArrayList
        System.out.println("Member deleted successfully");
    }

    // Method to display a member's details
    public void displayMemberDetails(int memberID) {
        Member member = searchMemberByID(memberID); // Search for the member

        if (member == null) {
            System.err.println("Member not found");
            return;
        }

        System.out.println(member.getMemberDetails()); // Display the member details
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
                System.out.println(member.getMemberDetails() + "\nMember ID: " + member.getMemberID()); // Display the member details
            }
        }
    }

    // Method to display all members at once
    public void displayAllMembers() {
        for (Member member : members) {
            System.out.println(member.getMemberDetails() + "\nMember ID: " + member.getMemberID());
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
