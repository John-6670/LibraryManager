package models.member;

import models.book.Book;
import models.book.BorrowStatus;
import models.base.LibraryItem;

import java.util.ArrayList;

/**
 * Represents a member of the library.
 *
 * @author John
 */
public class Member extends LibraryItem {
    private Byte age;
    private final Character GENDER;
    private final ArrayList<Book> BORROWED_BOOKS = new ArrayList<>(); // ArrayList to hold the books borrowed by the member
    public static final int MAX_BORROWED_BOOKS = 5; // Maximum number of books a member can borrow


    /**
     * Constructs a new Member with the given name, age, and gender.
     *
     * @param name   the name of the member
     * @param age    the age of the member
     * @param gender the gender of the member
     */
    public Member(String name, Byte age, Character gender) {
        super(name);
        this.age = age;
        this.GENDER = gender;
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
     * Returns the list of books borrowed by the member.
     *
     * @return an ArrayList of Book objects representing the books currently borrowed by the member
     */
    public ArrayList<Book> getBorrowedBooks() {
        return BORROWED_BOOKS;
    }

    /**
     * Returns a formatted string representation of the member for table display.
     * The string includes the member's ID, name, age, gender, number of borrowed books.
     *
     * @return a formatted string representation of the member for table display
     */
    @Override
    public String toStringTable() {
        String gender = genderConvertor();
        String age = ageConvertor();

        int numberOFBorrowedBooks = BORROWED_BOOKS.size();

        return String.format("| %-9s | %-18s | %-7s | %-7s | %-24d |", ID, name, age, gender, numberOFBorrowedBooks);
    }

    /**
     * Returns a string representation of the member.
     *
     * @return a string representation of the member
     */
    @Override
    public String toString() {
        String gender = genderConvertor();
        String age = ageConvertor();

        if (BORROWED_BOOKS.isEmpty()) {
            return "Member { memberID: " + ID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                    "borrowedBooks: None }";
        }

        ArrayList<String> borrowedBooksNames = new ArrayList<>();
        for (Book book : BORROWED_BOOKS) {
            borrowedBooksNames.add(book.getName() + "By: " + book.getAuthor());
        }

        return "Member { memberID: " + ID + ", name: '" + name + "', " + "age: " + age + ", gender: '" + gender + "', " +
                "borrowedBooks: " + borrowedBooksNames + " }";
    }

    /**
     * Returns gender of the member.
     *
     * @return the gender of the member
     */
    private String genderConvertor() {
        String gender;
        gender = switch (GENDER) {
            case 'M' -> "Male";
            case 'F' -> "Female";
            default -> "Unknown";
        };

        return gender;
    }

    /**
     * Returns age of the member.
     *
     * @return the age of the member
     */
    private String ageConvertor() {
        return age == null ? "Unknown" : String.valueOf(age);
    }

    /**
     * Attempts to borrow a book for the member.
     *
     * @param book the book to be borrowed
     * @return true if the book was successfully borrowed, false otherwise
     */
    public boolean borrowBook(Book book) {
        if (book.getBorrower() != null || book.getBorrowStatus() != BorrowStatus.AVAILABLE || BORROWED_BOOKS.size() >= MAX_BORROWED_BOOKS) {
            return false;
        }

        for (Book borrowedBook : BORROWED_BOOKS) {
            if (borrowedBook.getBorrowStatus() == BorrowStatus.EXPIRED) {
                return false;
            }
        }

        BORROWED_BOOKS.add(book); // Add the book to the member's borrowed books
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
        if (book.getBorrower() != this || book.getBorrowStatus() != BorrowStatus.BORROWED || !BORROWED_BOOKS.contains(book)) {
            return false;
        }

        BORROWED_BOOKS.remove(book); // Remove the book from the member's borrowed books
        book.setBorrower(null); // Set the book's borrower to null
        book.setBorrowStatus(BorrowStatus.AVAILABLE); // Set the book's borrow status to AVAILABLE
        book.setBorrowedTime(0); // Reset the book's borrowed time
        return true;
    }
}
