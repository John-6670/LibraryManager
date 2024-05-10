package models.book;

import models.member.Member;

/**
 * Represents a book in the library.
 * This class provides all the necessary details about a book, such as its title, author, genre, and borrow status.
 * It also includes methods to get and set these details.
 *
 * @author John
 */
public class Book {
    private static int nextBookID = 2024_03_06; // Static variable to hold the next book ID
    private static final int allowedBorrowTime = 120_000; // Time in ms
    private BorrowStatus borrowStatus = BorrowStatus.AVAILABLE; // Enum to hold the borrow status of the book
    private final int bookID;
    private String title;
    private String author;
    private final Genre genre;
    private Member borrower = null;
    private long borrowedTime;

    /**
     * Constructs a new Book object with the given title, author, and genre.
     * The bookID is automatically generated and incremented for each new book.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param genre  the genre of the book
     */
    public Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = nextBookID++;
    }

    // Getters and Setters

    /**
     * Returns the ID of the book.
     *
     * @return the book's ID
     */
    public int getBookID() {
        return bookID;
    }


    /**
     * Returns the title of the book.
     *
     * @return the book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the genre of the book.
     *
     * @return the book's genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the borrow status of the book.
     *
     * @return the book's borrow status
     */
    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    /**
     * Returns the member who borrowed the book.
     *
     * @return the member who borrowed the book
     */
    public Member getBorrower() {
        return borrower;
    }

    /**
     * Returns the remaining allowed time for the book to be returned.
     *
     * @return the remaining allowed time in milliseconds
     */
    public long getRemainAllowedTime() {
        if (borrowStatus == BorrowStatus.BORROWED) {
            long currentTime = System.currentTimeMillis();
            return allowedBorrowTime - (currentTime - borrowedTime);
        }

        return 120_000;
    }

    /**
     * Sets the borrow status of the book.
     *
     * @param borrowStatus the new borrow status
     */
    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    /**
     * Sets the member who borrowed the book.
     *
     * @param borrower the member who borrowed the book
     */
    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    /**
     * Sets the time when the book was borrowed.
     *
     * @param borrowedTime the time when the book was borrowed
     */
    public void setBorrowedTime(long borrowedTime) {
        this.borrowedTime = borrowedTime;
    }

    /**
     * Returns a formatted string representation of the book for table display.
     * The string includes the book's ID, title, author, genre, borrow status, and the ID of the borrower (if any).
     *
     * @return a formatted string representation of the book for table display
     */
    public String toStringTable() {
        String borrowerId = (borrower != null) ? String.valueOf(borrower.getMemberID()) : "None";
        return String.format("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |", bookID, title, author, genre, borrowStatus, borrowerId);
    }

    /**
     * Returns a string representation of the book.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        String borrower = this.borrower == null ? "No one" : this.borrower.getName();

        return "Book { Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre +
                ", Borrow Status: " + borrowStatus + ", Borrower: " + borrower + " }";
    }
}
