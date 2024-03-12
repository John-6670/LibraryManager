package models.book;

import models.member.Member;

public class Book {
    private static int nextBookID = 2024_03_06; // Static variable to hold the next book ID
    private final int bookID;
    private final String title;
    private final String author;
    private final Genre genre;
    private BorrowStatus borrowStatus = BorrowStatus.AVAILABLE; // Enum to hold the borrow status of the book
    private Member borrower = null;

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

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }
}
