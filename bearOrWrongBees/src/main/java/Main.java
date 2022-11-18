import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<List<String>> forest = setupForest();
        ExecutorService hive = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            futures.add(hive.submit(new MyFinder(forest.get(i), i + 1)));
        }

        for(Future<?> future : futures) {
            future.get();
        }

        hive.shutdown();
        System.out.println("Обращение к мирным жителям: Поздравляю граждане, голода не будет, неприятель отступил");
    }

    private static List<List<String>> setupForest() {
        List<List<String>> forest = new ArrayList<>();
        List<String> zone1 = List.of("tree", "hive", "river");
        List<String> zone2 = List.of("the rock Johnson", "tree", "tree");
        List<String> zone3 = List.of("pig", "bear", "bear's gun");
        forest.add(zone1);
        forest.add(zone2);
        forest.add(zone3);
        return forest;
    }


}
