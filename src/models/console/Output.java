package models.console;

/**
 * This class provides static methods for console output.
 * It includes methods to print a menu, clear the console screen, and pause execution until the user presses enter.
 *
 * @author John
 */
public class Output {
    /**
     * Prints a menu to the console.
     * The menu title is centered and the options are numbered.
     *
     * @param title The title of the menu.
     * @param options An array of strings representing the menu options.
     */
    public static void printMenu(String title, String[] options) {
        int paddingSize = (48 - title.length()) / 2;
        String padding = String.format("%" + paddingSize + "s", "");
        System.out.println(padding + title);
        System.out.println("------------------------------------------------");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    /**
     * Clears the console screen.
     * This is done by printing an ANSI escape code that moves the cursor to the home position (0,0).
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to move the cursor to the home position (0,0)
        System.out.flush(); // Flush the stream to ensure that the console is cleared immediately
    }

    /**
     * Pauses execution until the user presses enter.
     * This is done by printing a message and then waiting for the user to press enter.
     *
     * @param scan A Scanner object to read the user's input.
     */
    public static void pause(java.util.Scanner scan) {
        System.out.println("Press enter to continue...");
        scan.nextLine(); // Consume the newline character
        scan.nextLine(); // Wait for the user to press enter
    }
}
