package models.member;

import models.book.Book;
import models.book.BorrowStatus;

import java.util.ArrayList;

/**
 * Represents a member of the library.
 *
 * @author John
 */
public class Member {
    private static int nextMemberID = 2024_03_06; // Static variable to hold the next member ID
    private final int memberID;
    private String name;
    private Byte age;
    private final Character gender;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>(); // ArrayList to hold the books borrowed by the member
    public static final int MAX_BORROWED_BOOKS = 5; // Maximum number of books a member can borrow


    /**
     * Constructs a new Member with the given name, age, and gender.
     *
     * @param name   the name of the member
     * @param age    the age of the member
     * @param gender the gender of the member
     */
    public Member(String name, Byte age, Character gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.memberID = nextMemberID++;
    }

    /**
     * Sets the name of the member.
     *
     * @param name the new name of the member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the age of the member.
     *
     * @param age the new age of the member
     */
    public void setAge(byte age) {
        this.age = age;
    }

    /**
     * Returns the ID of the member.
     *
     * @return the ID of the member
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * Returns the name of the member.
     *
     * @return the name of the member
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the list of books borrowed by the member.
     *
     * @return an ArrayList of Book objects representing the books currently borrowed by the member
     */
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String toStringTable() {
        String gender;
        if (this.gender == null) {
            gender = "Unknown";
        } else {
            gender = switch (this.gender) {
                case 'M' -> "Male";
                default -> "Female";
            };
        }

        String age;
        if (this.age == null) {
            age = "Unknown";
        } else {
            age = String.valueOf(this.age);
        }

        int numberOFBorrowedBooks = borrowedBooks.size();

        return String.format("| %-9s | %-18s | %-7s | %-7s | %-24d |", memberID, name, age, gender, numberOFBorrowedBooks);
    }

    /**
     * Returns a string representation of the member.
     *
     * @return a string representation of the member
     */
    @Override
    public String toString() {
        String gender;
        if (this.gender == null) {
            gender = "Unknown";
        } else {
            gender = switch (this.gender) {
                case 'M' -> "Male";
                default -> "Female";
            };
        }

        String age;
        if (this.age == null) {
            age = "Unknown";
        } else {
            age = String.valueOf(this.age);
        }

        if (borrowedBooks.isEmpty()) {
            return "Member { memberID: " + memberID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                    "borrowedBooks: None }";
        }

        ArrayList<String> borrowedBooksNames = new ArrayList<>();
        for (Book book : borrowedBooks) {
            borrowedBooksNames.add(book.getTitle() + "By: " + book.getAuthor());
        }

        return "Member { memberID: " + memberID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                "borrowedBooks: " + borrowedBooksNames + " }";
    }

    /**
     * Attempts to borrow a book for the member.
     *
     * @param book the book to be borrowed
     * @return true if the book was successfully borrowed, false otherwise
     */
    public boolean borrowBook(Book book) {
        if (book.getBorrower() != null || book.getBorrowStatus() != BorrowStatus.AVAILABLE || borrowedBooks.size() >= MAX_BORROWED_BOOKS) {
            return false;
        }

        for (Book borrowedBook : borrowedBooks) {
            if (borrowedBook.getBorrowStatus() == BorrowStatus.EXPIRED) {
                return false;
            }
        }

        borrowedBooks.add(book); // Add the book to the member's borrowed books
        book.setBorrower(this); // Set the book's borrower to the member
        book.setBorrowStatus(BorrowStatus.BORROWED); // Set the book's borrow status to BORROWED
        book.setBorrowedTime(System.currentTimeMillis()); // Set the book's borrowed time to the current time
        return true;
    }

    /**
     * Attempts to return a book for the member.
     *
     * @param book the book to be returned
     * @return true if the book was successfully returned, false otherwise
     */
    public boolean returnBook(Book book) {
        if (book.getBorrower() != this || book.getBorrowStatus() != BorrowStatus.BORROWED || !borrowedBooks.contains(book)) {
            return false;
        }

        borrowedBooks.remove(book); // Remove the book from the member's borrowed books
        book.setBorrower(null); // Set the book's borrower to null
        book.setBorrowStatus(BorrowStatus.AVAILABLE); // Set the book's borrow status to AVAILABLE
        book.setBorrowedTime(0); // Reset the book's borrowed time
        return true;
    }
}
