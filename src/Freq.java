import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Freq implements Command{
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
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
        return false;
    }
}
