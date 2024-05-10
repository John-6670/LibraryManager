package models.book;

/**
 * This enum represents the possible borrowing statuses for a book.
 *
 * @author John
 */
public enum BorrowStatus {

    /**
     * The book is available for borrowing.
     */
    AVAILABLE,

    /**
     * The book is currently borrowed by someone.
     */
    BORROWED,

    /**
     * The book is borrowed and the borrowing period has expired.
     */
    EXPIRED
}

