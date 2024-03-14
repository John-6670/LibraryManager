package models.book;

import models.member.Member;

public class Book {
    private static int nextBookID = 2024_03_06; // Static variable to hold the next book ID
    private static final int allowedBorrowTime = 120_000; // Time in ms
    private BorrowStatus borrowStatus = BorrowStatus.AVAILABLE; // Enum to hold the borrow status of the book
    private final int bookID;
    private final String title;
    private final String author;
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
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public Member getBorrower() {
        return borrower;
    }

    public boolean isAvailable() {
        return borrowStatus == BorrowStatus.AVAILABLE;
    }

    public long getRemainAllowedTime() {
        if (borrowStatus == BorrowStatus.BORROWED) {
            long currentTime = System.currentTimeMillis();
            return allowedBorrowTime - (currentTime - borrowedTime);
        }

        return 120_000;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        String borrower = this.borrower == null ? "No one" : this.borrower.getName();

        return "Book { Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre +
                ", Borrow Status: " + borrowStatus + ", Borrower: " + borrower + " }";
    }

    public void setBorrowedTime(long borrowedTime) {
        this.borrowedTime = borrowedTime;
    }
}
