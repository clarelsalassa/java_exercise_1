import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {

    public static void fibo(Scanner scanner){
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
    }

    public static void freq(Scanner scanner){
        System.out.println("Entrez le chemin d'accès au fichier:");
        String input = scanner.nextLine();
        Path path = Paths.get(input);
        try {
            String content = Files.readString(path);
            content.replaceAll("[^a-zA-Z0-9]+", " ").toLowerCase();
            String[] array = content.split(" ");
            ArrayList<String> res = new ArrayList<String>();
            int x = 3;
            while (x > 0) {
                int maxCount = 0;
                int count = 0;
                String word = "";
                for (int i = 0; i < array.length; i++) {
                    if (!res.contains(array[i])){
                        count = 1;
                        for (int j = i + 1; j < array.length; j++){
                            if (array[i].equals(array[j])){
                                count++;
                            }
                        }
                        if (count > maxCount){
                            maxCount = count;
                            word = array[i];
                        }
                    }

                }
                res.add(word);
                x -= 1;
            }
            for (int y = 0; y < res.size(); y++){
                if (y != res.size() - 1) {
                    System.out.print(res.get(y) + " ");
                }
                else {
                    System.out.print(res.get(y));
                }
            }
            System.out.println();
            //System.out.println("Les 3 mots les plus fréquents sont: " + res.toString());

        }
        catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans la faille de l'invocateur");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("fibo")){
                fibo(scanner);
            }
            else if (input.equals("freq")){
                freq(scanner);
            }
            else if (!input.equals("quit")) {
                System.out.println("Unknown command");
            }
            else{
                break;
            }
        }
    }
}
