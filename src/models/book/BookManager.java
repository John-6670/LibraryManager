package models.book;

import models.base.Manager;
import models.console.*;

import java.util.ArrayList;

/**
 * This class manages the books in the library.
 * It provides functionalities to add, display, search, and delete books.
 *
 * @author John
 */
public class BookManager implements Manager{
    private final ArrayList<Book> bookList; // Create an ArrayList to hold books
    private static final String[] menuItems = new String[] {
            "Add Book",
            "Display all books",
            "Update book",
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
    @Override
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            Output.printMenu("Book Manager", menuItems);
            int choice = Input.scan.nextInt();
            Output.clearScreen();

            switch (choice) {
                case 1:
                    add();
                    break;

                case 2:
                    list();
                    break;

                case 3:
                    System.out.println("Enter ID: ");
                    int id = Input.scan.nextInt();
                    update(id);

                case 4:
                    System.out.print("Enter title: ");
                    String title = Input.scan.next();
                    search(title);
                    break;

                case 5:
                    System.out.print("Enter author: ");
                    String author = Input.scan.next();
                    searchBookByAuthor(author);
                    break;

                case 6:
                    Output.printMenu("Genres", Genre.getGenresAsString());
                    int genreChoice = Input.scan.nextInt();
                    Genre genre = Genre.values()[genreChoice - 1];
                    searchBookByGenre(genre);
                    break;

                case 7:
                    System.out.print("Enter ID: ");
                    id = Input.scan.nextInt();
                    Book result = searchByID(id);
                    if (result == null) {
                        System.err.println("Book not found");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case 8:
                    System.out.println("Enter ID: ");
                    id = Input.scan.nextInt();
                    delete(id);
                    break;

                case 9:
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
    @Override
    public void add() {
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
    @Override
    public void list() {
        System.out.printf ("| %-8s | %-18s | %-13s | %-15s | %-13s | %-13s |\n", "Book ID", "Title", "Author", "Genre", "Borrow Status", "Borrower ID");
        System.out.println("|----------|--------------------|---------------|-----------------|---------------|---------------|");

        for (Book book : bookList) {
            System.out.println(book.toStringTable());
        }
    }

    /**
     * Updates a book in the library.
     *
     * @param id The ID of the book to update.
     */
    @Override
    public void update(int id) {
        Book book = searchByID(id);

        if (book == null) {
            System.err.println("Book not found");
            return;
        }

        System.out.println("Enter new title: ");
        String title = Input.scan.next();
        title = title.toUpperCase().charAt(0) + title.substring(1).toLowerCase(); // Capitalize the first letter

        System.out.println("Enter new author: ");
        String author = Input.scan.next();
        author = author.toUpperCase().charAt(0) + author.substring(1).toLowerCase(); // Capitalize the first letter

        book.setName(title);
        book.setAuthor(author);

        System.out.println("Book updated successfully");
    }

    /**
     * Searches for a book by its title.
     *
     * @param title The title to search for.
     */
    @Override
    public void search(String title) {
        ArrayList<Book> searchResult = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getName().toLowerCase().contains(title.toLowerCase())) {
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
     * Deletes a book from the library by its ID.
     *
     * @param id The ID of the book to delete.
     */
    @Override
    public void delete(int id) {
        bookList.removeIf(book -> book.getID() == id);
    }

    /**
     * Searches for a book by its ID.
     *
     * @param id The ID to search for.
     */
    public Book searchByID(int id) {
        // Initialize left and right pointers for binary search
        int left = 0;
        int right = bookList.size() - 1;

        // While the search space is not exhausted
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate the middle index
            Book book = bookList.get(mid); // Get the book at the middle index

            // If the book's ID matches the search ID, return the book
            if (book.getID() == id) {
                return book;
            }

            // If the book's ID is less than the search ID, discard the left half
            if (book.getID() < id) {
                left = mid + 1;
            } else {
                // Otherwise, discard the right half
                right = mid - 1;
            }
        }

        return null;
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
}
