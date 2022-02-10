import java.util.Scanner;

public class Fibo implements Command{
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Entrez un nombre pour l'index de la suite de " +
                "fibonacci:");
        int value = Integer.valueOf(scanner.nextLine());
        int f0 = 0;
        int f1 = 1;
        int ff;
        for (int i = 1; i < value; i++){
            ff = f1;
            f1 = f0 + f1;
            f0 = ff;
        }
        if (value == 0)
        {
            System.out.println("F(" + value + ") = " + 0);
        }
        else
        {
            System.out.println("F(" + value + ") = " + f1);
        }
        return false;
    }
}
