package models.book;

import java.util.ArrayList;
import java.util.Scanner;

import models.console.Output;
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

            Output.pause();
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

    public void searchBookByID(int id) {
        for (Book book : bookList) {
            if (book.getBookID() == id) {
                System.out.println(book);
            }
        }
    }
}
