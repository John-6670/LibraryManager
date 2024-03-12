package models.book;

import java.util.ArrayList;

public class BookManager {
    private final ArrayList<Book> bookList = new ArrayList<>();

    public void start(java.util.Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Add Book");
            System.out.println("2. Display all books");
            System.out.println("3. Search book by title");
            System.out.println("4. Search book by author");
            System.out.println("5. Search book by ISBN");
            System.out.println("6. Delete book");
            System.out.println("7. Go back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    searchBookByTitle(scanner);
                    break;
                case 4:
                    searchBookByAuthor(scanner);
                    break;
                case 5:
                    // searchBookByISBN(scanner);
                    break;
                case 6:
                    // deleteBook(scanner);
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void addBook(java.util.Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.next();
        System.out.print("Enter author: ");
        String author = scanner.next();
        System.out.print("Enter Genre: ");
        Genre genre = Genre.valueOf(scanner.next().toUpperCase());
        Book book = new Book(title, author, genre);
        bookList.add(book);
    }

    public void displayAllBooks() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void searchBookByTitle(java.util.Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.next();
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
            }
        }
    }

    public void searchBookByAuthor(java.util.Scanner scanner) {
        System.out.print("Enter author: ");
        String author = scanner.next();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                System.out.println(book);
            }
        }
    }
}
