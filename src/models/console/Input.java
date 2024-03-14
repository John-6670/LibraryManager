package models.console;

import java.util.Scanner;

/**
 * This class provides a static Scanner object for console input.
 * It is used throughout the application to read user input from the console.
 *
 * @author John
 */
public class Input {
    /**
     * A static Scanner object that reads input from the console.
     * It is public so that it can be accessed directly from other classes.
     */
    public static Scanner scan = new Scanner(System.in);
}
