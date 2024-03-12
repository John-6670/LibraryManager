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
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pause() {
        System.out.println("Press enter to continue...");
        try {
            System.in.skip(System.in.available());
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
