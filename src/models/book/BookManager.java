package models.book;

import models.console.*;

import java.util.ArrayList;

/**
 * This class manages the books in the library.
 * It provides functionalities to add, display, search, and delete books.
 *
 * @author John
 */
public class BookManager {
    private final ArrayList<Book> bookList; // Create an ArrayList to hold books
    private static final String[] menuItems = new String[] {
            "Add Book",
            "Display all books",
            "Search book by title",
            "List books by author",
            "Search book by genre",
            "Search book by ID",
            "Delete book",
            "Go back"
    }; // Menu items

    /**
     * Constructor for BookManager class.
     * Initializes the ArrayList of books.
     */
    public BookManager() {
        bookList = new ArrayList<>();
    }

    /**
     * Getter for the book list.
     *
     * @return The ArrayList of books.
     */
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    /**
     * Starts the book manager.
     * Displays a menu to the user and performs actions based on the user's choice.
     */
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            Output.printMenu("Book Manager", menuItems);
            int choice = Input.scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    addBook();
                    break;

                case 2:
                    ShowAllBooks();
                    break;

                case 3:
                    System.out.print("Enter title: ");
                    String title = Input.scan.next();
                    searchBookByTitle(title);
                    break;

                case 4:
                    System.out.print("Enter author: ");
                    String author = Input.scan.next();
                    searchBookByAuthor(author);
                    break;

                case 5:
                    Output.printMenu("Genres", Genre.getGenresAsString());
                    int genreChoice = Input.scan.nextInt();
                    Genre genre = Genre.values()[genreChoice - 1];
                    searchBookByGenre(genre);
                    break;

                case 6:
                    System.out.print("Enter ID: ");
                    int id = Input.scan.nextInt();
                    Book result = searchBookByID(id);
                    if (result != null) {
                        System.out.println(result);
                    } else {
                        System.err.println("Book not found");
                    }
                    break;

                case 7:
                    System.out.println("Enter ID: ");
                    id = Input.scan.nextInt();
                    bookList.removeIf(book -> book.getBookID() == id);
                    break;

                case 8:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            if (isRunning) {
                Output.pause(Input.scan);
            }
            Output.clearScreen();
        }
    }

    /**
     * Starts the book manager.
     * Displays a menu to the user and performs actions based on the user's choice.
     */
    public void addBook() {
        System.out.print("Enter title: ");
        String title = Input.scan.next();
        title = title.toUpperCase().charAt(0) + title.substring(1).toLowerCase(); // Capitalize the first letter

        System.out.print("Enter author: ");
        String author = Input.scan.next();
        author = author.toUpperCase().charAt(0) + author.substring(1).toLowerCase(); // Capitalize the first letter

        Output.printMenu("Genres", Genre.getGenresAsString());
        int choice = Input.scan.nextInt();
        Genre genre = Genre.values()[choice - 1];

        Book book = new Book(title, author, genre);
        bookList.add(book);
        System.out.println(book + " has been added");
    }

    /**
     * Displays all books in the library.
     */
    public void ShowAllBooks() {
        System.out.printf ("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |\n", "Book ID", "Title", "Author", "Genre", "Borrow Status", "Borrower ID");
        System.out.println("|----------|--------------------|---------------|-----------------|---------------|---------------|");

        for (Book book : bookList) {
            System.out.println(book.toStringTable());
        }
    }

    /**
     * Searches for a book by its title.
     *
     * @param title The title to search for.
     */
    public void searchBookByTitle(String title) {
        ArrayList<Book> searchResult = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchResult.add(book);
            }
        }

        if (searchResult.isEmpty()) {
            System.err.println("No books found with the given title");
        } else {
            System.out.printf ("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |\n", "Book ID", "Title", "Author", "Genre", "Borrow Status", "Borrower ID");
            System.out.println("|----------|--------------------|---------------|-----------------|---------------|---------------|");

            for (Book book : searchResult) {
                System.out.println(book.toStringTable());
            }
        }
    }

    /**
     * Searches for a book by its author.
     *
     * @param author The author to search for.
     */
    public void searchBookByAuthor(String author) {
        ArrayList<Book> searchResult = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                searchResult.add(book);
            }
        }

        if (searchResult.isEmpty()) {
            System.err.println("No books found with the given author");
        } else {
            System.out.printf ("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |\n", "Book ID", "Title", "Author", "Genre", "Borrow Status", "Borrower ID");
            System.out.println("|----------|--------------------|---------------|-----------------|---------------|---------------|");

            for (Book book : searchResult) {
                System.out.println(book.toStringTable());
            }
        }
    }

    /**
     * Searches for a book by its genre.
     *
     * @param genre The genre to search for.
     */
    public void searchBookByGenre(Genre genre) {
        ArrayList<Book> searchResult = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getGenre() == genre) {
                searchResult.add(book);
            }
        }

        if (searchResult.isEmpty()) {
            System.err.println("No books found with the given genre");
        } else {
            System.out.printf ("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |\n", "Book ID", "Title", "Author", "Genre", "Borrow Status", "Borrower ID");
            System.out.println("|----------|--------------------|---------------|-----------------|---------------|---------------|");

            for (Book book : searchResult) {
                System.out.println(book.toStringTable());
            }
        }
    }

    /**
     * Searches for a book by its ID.
     *
     * @param id The ID to search for.
     *
     * @return The Book object if found, null otherwise.
     */
    public Book searchBookByID(int id) {
        // Initialize left and right pointers for binary search
        int left = 0;
        int right = bookList.size() - 1;

        // While the search space is not exhausted
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate the middle index
            Book book = bookList.get(mid); // Get the book at the middle index

            // If the book's ID matches the search ID, return the book
            if (book.getBookID() == id) {
                return book;
            }

            // If the book's ID is less than the search ID, discard the left half
            if (book.getBookID() < id) {
                left = mid + 1;
            } else {
                // Otherwise, discard the right half
                right = mid - 1;
            }
        }

        return null; // If the book is not found, return null
    }
}
