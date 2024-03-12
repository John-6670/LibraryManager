import java.util.Scanner;
import models.Library;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.start(scanner);
    }
}
