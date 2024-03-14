package models.console;

public class Output {
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to move the cursor to the home position (0,0)
        System.out.flush(); // Flush the stream to ensure that the console is cleared immediately
    }

    public static void pause(java.util.Scanner scan) {
        System.out.println("Press enter to continue...");
        scan.nextLine(); // Consume the newline character
        scan.nextLine(); // Wait for the user to press enter
    }
}
