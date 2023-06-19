import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ConsoleView {

    public void start() {
        System.out.println("Добро пожаловать на розыгрыш игрушек");
    }

    public String waitAction() {
        String[] choices = new String[]{"1", "2", "3", "q"};
        String input = "";
        Scanner scan = new Scanner(System.in);
        while (!Arrays.asList(choices).contains(input)) {
            System.out.println("1 - посмотреть список игрушек 2 - получить новые игрушки 3 - разыграть игрушки q - выйти");
            input = scan.nextLine();
            if (!Arrays.asList(choices).contains(input)) {
                System.out.println("Неправильный ввод");
            }
            return input;
        }
        return null;
    }

    public void showToys(List<Toy> list, String string) {
        StringBuilder text = new StringBuilder(string + "\n" + "_".repeat(100) + "\n");
        for (Toy o : list) {
            if (o != null)
                text.append(o).append("\n");
            else text.append("не повезло").append("\n");
        }
        System.out.println(text);
    }

    public void show(String text){
        System.out.println(text);
    }
}