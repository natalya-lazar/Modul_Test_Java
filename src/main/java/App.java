import java.util.*;

public class App {
    private PrizeToysDraw prizeToysDraw = new PrizeToysDraw();
    private String beforeFile = "beforeDrawToysPool.csv";

    private String toysPoolFile = "toysPool.csv";

    private String prizeToysFile = "prizeToys.txt";

    ConsoleView view = new ConsoleView();

    public App() {
        prizeToysDraw = new PrizeToysDraw();
    }

    public List<Toy> prizeDraw(int count) {
        save(prizeToysDraw.toStringCsv(), beforeFile);
        save("Выданные призы", prizeToysFile);
        Toy toy;
        List<Toy> prizeToys = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            prizeToysDraw.dropPrizeToy();
            toy = prizeToysDraw.getPrizeToy();
            prizeToys.add(toy);
            WorkWithFiles.writePrizeToy(toy,
                    "", prizeToysFile);
        }
        save(prizeToysDraw.toStringCsv(), toysPoolFile);
        return prizeToys;
    }

    public void createRandomToysFile() {
        Random rnd = new Random();
        PrizeToysDraw draw = new PrizeToysDraw();
        for (int i = 0; i < 5; i++) {
            draw.addToy(getToyName(), rnd.nextInt(1, 5), rnd.nextInt(1, 101));
        }
        save(draw.toStringCsv(), toysPoolFile);
    }

    private void save(String text, String filename) {
        WorkWithFiles.write(text, filename);
    }

    private static String getToyName() {
        return ToysNames.values()[new Random().nextInt(ToysNames.values().length)].toString();
    }

    public void loadPrizeToysPool() {
        prizeToysDraw.addingFromCsv(WorkWithFiles.readLinesToArrayList(toysPoolFile));
    }

    public void start() {
        view.start();
        String action = "";
        while (!action.equals("q")) {
            action = view.waitAction();
            switch (action) {
                case "1" -> view.showToys(prizeToysDraw.getToysPool(), "Список игрушек:");
                case "2" -> {
                    prizeToysDraw = new PrizeToysDraw();
                    createRandomToysFile();
                    loadPrizeToysPool();
                    view.show("Получены новые игрушки");
                }
                case "3" -> {
                    if (!prizeToysDraw.getToysPool().isEmpty())
                        view.showToys(prizeDraw(10), "Розыгрыш:");
                    else view.show("Игрушки закончились");
                }
            }
        }
    }

}
