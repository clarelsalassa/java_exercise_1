import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans la faille de l'invocateur");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if (!s.equals("quit"))
        {
            System.out.println("Unknown command");
        }
    }
}
