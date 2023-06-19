import java.util.*;
import java.util.stream.Collectors;

public class PrizeToysDraw {

    private List<Toy> toysPool;
    private Queue<Toy> prizeToys;

    private int dropRate;

    private int idCounter;

    public PrizeToysDraw() {
        toysPool = new ArrayList<>();
        prizeToys = new LinkedList<>();
        idCounter = 0;
    }

    public void addToy(String name, int count, int dropRate) {
        if (count > 0)
            toysPool.add(new Toy(idCounter++,name, count, dropRate));
    }

    public void addToy(int idCounter, String name, int count, int dropRate) {
        if (count > 0)
            toysPool.add(new Toy(idCounter, name, count, dropRate));
    }

    public void dropPrizeToy() {
        Random rnd = new Random();
        dropRate = rnd.nextInt(0, 101);
        List<Toy> prizePool = new ArrayList<>();
        prizePool = toysPool.stream().filter(toy -> toy.getDropRate() >= dropRate).collect(Collectors.toList());
        if (prizePool.size() > 0) {
            prizeToys.add(prizePool.get(rnd.nextInt(0, prizePool.size())));
        }
    }

    public Toy getPrizeToy() {
        if (prizeToys.size() > 0) {
            Toy prizeToy = prizeToys.poll();
            prizeToy.setCount(prizeToy.getCount() - 1);
            if (prizeToy.getCount() == 0) {
                toysPool.remove(prizeToy);
            }
            return prizeToy;
        }
        return null;
    }

    public String toStringCsv() {
        StringBuilder text = new StringBuilder();
        text.append("id").append(";").append("name").append(";").append("count").append(";").append("drop_rate");
        text.append("\n");
        for (Toy toy : toysPool) {
            text.append(toy.getId()).append(";").append(toy.getName()).append(";").append(toy.getCount()).append(";").append(toy.getDropRate());
            text.append("\n");
        }
        return text.toString();
    }

    public int getDropRate() {
        return dropRate;
    }

    public void addingFromCsv(String csvReadLines) {
        String[] data = csvReadLines.split("\n");
        for (int i = 1; i < data.length; i++) {
            String[] line = data[i].split(";");
            addToy(Integer.parseInt(line[0]), line[1] ,Integer.parseInt(line[2]),
                    Integer.parseInt(line[3].substring(0, line[3].length() - 1)));
        }
    }

    public List<Toy> getToysPool() {
        return toysPool;
    }
}
