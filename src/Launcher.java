import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {

    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans la faille de l'invocateur");
        Scanner scanner = new Scanner(System.in);
        List<Command> commands = new ArrayList<Command>();
        Command fibo = new Fibo();
        Command freq = new Freq();
        Command quit = new Quit();
        commands.add(fibo);
        commands.add(freq);
        commands.add(quit);
        boolean b = false;
        while (b == false) {
            String input = scanner.nextLine();
            int i = 0;
            for ( ; i < commands.size(); i++){
                if (input.equals(commands.get(i).name())){
                    b = commands.get(i).run(scanner);
                    break;
                }
            }
            if (i == commands.size()){
                System.out.println("Unknown command");
            }
        }
    }
}
