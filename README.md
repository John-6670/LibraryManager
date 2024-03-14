# Library Management System
This project is a simple Library Management System implemented in Java. It provides a variety of functionalities to manage books, manage members, and borrow/return books

## features
- **Book Management:** The system allows you to add, update, delete, display and search books. Each book has its own unique ID, title, author, and borrow status. The borrow status can be either available, borrowed, or expired.
- **Member Management:** The system allows you to add, update, delete, display and search members. Each member has its own unique ID, name, age, and gender. Members can borrow up to 5 books at a time.
- **Borrow/Return Books:** Members can borrow available books and return them when they are done. The system keeps track of the borrow status and borrowed time of each book. If a book is not returned within the allowed time, its borrow status becomes expired.

### Classes
- **Main:** This is the main class that starts the Library Management System.
- **Library:** This class represents a library, which manages books and members.
- **BookManager:** This class provides functionalities to manage books, such as add, update, delete, display, and search books.
- **MemberManager:** This class provides functionalities to manage members, such as add, update, delete, display, and search members.
- **Book:** This class represents a book, which has a unique ID, title, author, and borrow status.
- **Member:** This class represents a member, which has a unique ID, name, age, and gender.
- **Output:** This class provides static methods to display output in a formatted way.
- **Input:** This class provides a static Scanner object for console input.

### How to run
1. Clone the repository
2. Open the project in an IDE
3. Run the Main class
4. Follow the instructions in the console to use the Library Management System
5. Enjoy!

## Example
```
           Library Management System
------------------------------------------------
1. Book Manager
2. Member Manager
3. Borrow/Return Book
4. Exit
------------------------------------------------
Please enter your choice: 1

                  Book Manager
------------------------------------------------
1. Add Book
2. Display all books
3. Search book by title
4. List books by author
5. Search book by genre
6. Search book by ID
7. Delete book
8. Go back
------------------------------------------------
Please enter your choice: 1

Enter title: Angels and Demons
Enter author: Dan Brown

                      Genres
------------------------------------------------
1. FICTION
2. NON_FICTION
3. SCIENCE_FICTION
4. MYSTERY
5. THRILLER
6. ROMANCE
7. FANTASY
8. HORROR
9. BIOGRAPHY
10. HISTORY
11. CHILDREN
12. SCIENCE
13. POETRY
14. DICTIONARY
15. COMICS
------------------------------------------------
Please enter the genre number: 4
Book { Book ID: 20240306, Title: Angels and Demons, Author: Dan Brown, Genre: MYSTERY, Borrow Status: AVAILABLE, Borrower: No one } has been added
Press enter to continue...
```

### Author
[John-6670](https://github.com/John-6670/)

### Note
This project is still under development. More features and improvements are coming soon.