package models.book;

import java.util.ArrayList;
import java.util.Scanner;

import models.console.Output;
import models.member.Member;
import models.menu.Menu;

public class BookManager extends Menu {
    private final ArrayList<Book> bookList;
    private static final Scanner scan = new Scanner(System.in);

    public BookManager() {
        bookList = new ArrayList<>();
        menuItems = new String[] {
            "Add Book",
            "Display all books",
            "Search book by title",
            "Search book by author",
            "Search book by genre",
            "Search book by ID",
            "Delete book",
            "Exit"
        };
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            Output.printMenu("Book Manager", menuItems);
            int choice = scan.nextInt();
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
                    String title = scan.next();
                    searchBookByTitle(title);
                    break;

                case 4:
                    System.out.print("Enter author: ");
                    String author = scan.next();
                    searchBookByAuthor(author);
                    break;

                case 5:
                    System.out.print("Enter genre: ");
                    Genre genre = Genre.valueOf(scan.next().toUpperCase());
                    searchBookByGenre(genre);
                    break;

                case 6:
                    System.out.print("Enter ID: ");
                    int id = scan.nextInt();
                    searchBookByID(id);
                    break;

                case 7:
                    System.out.println("Enter ID: ");
                    id = scan.nextInt();
                    bookList.removeIf(book -> book.getBookID() == id);
                    break;

                case 8:
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            Output.pause(scan);
            Output.clearScreen();
        }
    }

    public void addBook() {
        System.out.print("Enter title: ");
        String title = scan.next();
        System.out.print("Enter author: ");
        String author = scan.next();
        System.out.print("Enter Genre: ");
        Genre genre = Genre.valueOf(scan.next().toUpperCase());
        Book book = new Book(title, author, genre);
        bookList.add(book);
    }

    public void displayAllBooks() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void searchBookByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
            }
        }
    }

    public void searchBookByAuthor(String author) {
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                System.out.println(book);
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
