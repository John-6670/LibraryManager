package models.member;

import models.book.Book;
import models.book.BorrowStatus;

import java.util.ArrayList;

public class Member {
    private static int nextMemberID = 2024_03_06; // Static variable to hold the next member ID
    private final int memberID;
    private String name;
    private byte age;
    private final char gender;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>(); // ArrayList to hold the books borrowed by the member

    // Constructor
    public Member(String name, byte age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.memberID = nextMemberID++;
    }

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member { memberID: " + memberID + ", name: '" + name + "', " + "age: " + age + ", gender = '" + gender + "', " +
                "borrowedBooks = " + borrowedBooks.toString();
    }

    public boolean borrowBook(Book book) {
        if (book.getBorrower() == null || book.getBorrowStatus() != BorrowStatus.AVAILABLE) {
            return false;
        }

        book.setBorrower(this);
        book.setBorrowStatus(BorrowStatus.BORROWED);
        return true;
    }

    public boolean returnBook(Book book) {
        if (book.getBorrower() != this || book.getBorrowStatus() != BorrowStatus.BORROWED) {
            return false;
        }

        book.setBorrower(null);
        book.setBorrowStatus(BorrowStatus.AVAILABLE);
        return true;
    }
}
