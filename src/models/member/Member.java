package models.member;

import models.book.Book;
import models.book.BorrowStatus;

import java.util.ArrayList;

/**
 * Represents a member of the library.
 */
public class Member {
    private static int nextMemberID = 2024_03_06; // Static variable to hold the next member ID
    private final int memberID;
    private String name;
    private byte age;
    private final char gender;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>(); // ArrayList to hold the books borrowed by the member
    public static final int MAX_BORROWED_BOOKS = 5; // Maximum number of books a member can borrow


    /**
     * Constructs a new Member with the given name, age, and gender.
     *
     * @param name   the name of the member
     * @param age    the age of the member
     * @param gender the gender of the member
     */
    public Member(String name, byte age, char gender) {
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

    /**
     * Returns a string representation of the member.
     *
     * @return a string representation of the member
     */
    @Override
    public String toString() {
        String gender = switch (this.gender) {
            case 'M' -> "Male";
            case 'F' -> "Female";
            default -> "Unknown";
        };

        if (borrowedBooks.isEmpty()) {
            return "Member { memberID: " + memberID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                    "borrowedBooks: None }";
        }

        return "Member { memberID: " + memberID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                "borrowedBooks: " + borrowedBooks + " }";
    }

    /**
     * Attempts to borrow a book for the member.
     *
     * @param book the book to be borrowed
     * @return true if the book was successfully borrowed, false otherwise
     */
    public boolean borrowBook(Book book) {
        if (book.getBorrower() == null || book.getBorrowStatus() != BorrowStatus.AVAILABLE) {
            return false;
        }

        book.setBorrower(this);
        book.setBorrowStatus(BorrowStatus.BORROWED);
        book.setBorrowedTime(System.currentTimeMillis());
        return true;
    }

    /**
     * Attempts to return a book for the member.
     *
     * @param book the book to be returned
     * @return true if the book was successfully returned, false otherwise
     */
    public boolean returnBook(Book book) {
        if (book.getBorrower() != this || book.getBorrowStatus() != BorrowStatus.BORROWED) {
            return false;
        }

        book.setBorrower(null);
        book.setBorrowStatus(BorrowStatus.AVAILABLE);
        book.setBorrowedTime(0);
        return true;
    }
}
