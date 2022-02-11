import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Predict implements Command{
    @Override
    public String name() {
        return "predict";
    }

    public List<String> nextWords(List<String> words, String startWord){

        Map<String, List<String>> wordsAfter = computeDictionary(words);

        Map<String, String> wordAfterEach = computeWordAfterEach(wordsAfter);

        List<String> sentence = new ArrayList<>();
        sentence.add(startWord);
        for (int i = 0; i < 20; i++){
            String currentWord = sentence.get(sentence.size() - 1);
            String nextWord = wordAfterEach.get(currentWord.toLowerCase());
            sentence.add(nextWord);
        }
        return sentence;
    }

    private Map<String, List<String>> computeDictionary(List<String> words){
        Map<String, List<String>> wordsAfter = new HashMap<>();

        for (int i = 0; i < words.size() - 1; i++){
            String word = words.get(i);

            wordsAfter.computeIfAbsent(word, w -> new ArrayList<>()).add(words.get(i + 1));
        }
        return wordsAfter;
    }

    private String mostOccurring(List<String> words){
        Map<String, Long> countByWords = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> entries = new ArrayList<>(countByWords.entrySet());
        entries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        return entries.get(0).getKey();
    }

    private Map<String, String> computeWordAfterEach(Map<String, List<String>> wordsAfter){
        record Pair(String word, String wordAfter) {
        }
        Map<String, String> wordAfterEach = wordsAfter.entrySet()
                .stream()
                .map(e -> new Pair(e.getKey(), mostOccurring(e.getValue())))
                .collect(Collectors.toMap(p -> p.word(), p -> p.wordAfter()));
        return wordAfterEach;
    }


    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Entrez le chemin d'accès au fichier:");
        String input = scanner.nextLine();
        Path path = Paths.get(input);
        try {
            String fileContent = Files.readString(path).toLowerCase(Locale.ROOT);
            List<String> tableau = List.of(fileContent.replaceAll("[^a-zA-Z0-9]+", " ")
                    .split(" "));

            System.out.println("Entrez le Mot pour commencer la phrase:");
            input = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (tableau.contains(input)){
                List<String> sentence = nextWords(tableau, input);
                for (int i = 0; i < sentence.size(); i++){
                    String toPrint = sentence.get(i);
                    if (i == sentence.size() - 1){
                        System.out.println(toPrint);
                    }
                    else {
                        System.out.printf(sentence.get(i) + " ");
                    }
                }
            }
            else {
                throw new Exception();
            }

        }
        catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        } catch (Exception e) {
            System.out.println("Ce mot n'est pas présent: " + e);
        }
        return false;
    }
}
