import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
            String fileContent = Files.readString(path);
            String[] tableau = fileContent.replaceAll("[^a-zA-Z0-9]+", " ").toLowerCase()
                    .split(" ");
            var res = Arrays.stream(tableau).
                    collect(Collectors.groupingBy(e->e, Collectors.counting()));
            var res2 = res.entrySet().stream().
                    sorted(Comparator.comparing(Map.Entry<String,Long>::getValue).reversed()).limit(3);
            var res3 = res2.collect(Collectors.toList());
            for (int i = 0; i < res3.size(); i++){
                if (i == res3.size() - 1){
                    System.out.printf(res3.get(i).getKey().toString());
                }
                else {
                    System.out.printf(res3.get(i).getKey().toString() + ' ');
                }

            }
            System.out.println();

        }
        catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        }
        return false;
    }
}
/*
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
        System.out.println(); */
//System.out.println("Les 3 mots les plus fréquents sont: " + res.toString());
