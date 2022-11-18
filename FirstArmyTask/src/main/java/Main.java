import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        List<Integer> warehouse = setupWareHouse();
        Set<Loot> truck = new HashSet<>();
        List<Loot> looted = new ArrayList<>();
        List<Future<?>> futures = new ArrayList<>();

        ExecutorService robbers = Executors.newFixedThreadPool(3);
        futures.add(robbers.submit(new MyProducer(warehouse, looted)));
        futures.add(robbers.submit(new MyConsumer(truck, looted)));
        futures.add(robbers.submit(new MyAppraiser(looted)));
        robbers.shutdown();

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static List<Integer> setupWareHouse() {
        List<Integer> warehouse = new ArrayList<>();
        for(int i = 1; i < 2_000_000; i++) {
            warehouse.add(i);
        }
        return warehouse;
    }
}
