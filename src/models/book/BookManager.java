package models.book;

import models.console.*;

import java.util.ArrayList;

public class BookManager {
    private final ArrayList<Book> bookList; // Create an ArrayList to hold books
    private static final String[] menuItems = new String[] {
            "Add Book",
            "Display all books",
            "Search book by title",
            "Search book by author",
            "Search book by genre",
            "Search book by ID",
            "Delete book",
            "Exit"
    }; // Menu items

    public BookManager() {
        bookList = new ArrayList<>();
    }

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
                    displayAllBooks();
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
                    searchBookByID(id);
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

            Output.pause(Input.scan);
            Output.clearScreen();
        }
    }

    public void addBook() {
        System.out.print("Enter title: ");
        String title = Input.scan.next();
        System.out.print("Enter author: ");
        String author = Input.scan.next();
        Output.printMenu("Genres", Genre.getGenresAsString());
        int choice = Input.scan.nextInt();
        Genre genre = Genre.values()[choice - 1];

        Book book = new Book(title, author, genre);
        bookList.add(book);
        System.out.println(book + " has been added");
    }

    public void displayAllBooks() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

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
            System.out.println("Search results:");
            for (Book book : searchResult) {
                System.out.println(book); // Display the member details
            }
        }
    }

    public void searchBookByAuthor(String author) {
        ArrayList<Book> searchResult = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                searchResult.add(book);
            }
        }

        if (searchResult.isEmpty()) {
            System.err.println("No books found with the given author");
        } else {
            System.out.println("Search results:");
            for (Book book : searchResult) {
                System.out.println(book); // Display the member details
            }
        }
    }

    public void searchBookByGenre(Genre genre) {
        for (Book book : bookList) {
            if (book.getGenre() == genre) {
                System.out.println(book);
            }
        }
    }

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
