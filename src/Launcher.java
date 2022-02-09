import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans la faille de l'invocateur");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (!input.equals("quit")) {
                System.out.println("Unknown command");
            }
            else
            {
                break;
            }
        }
    }
}
