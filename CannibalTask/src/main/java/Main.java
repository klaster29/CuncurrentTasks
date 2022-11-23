import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5, true);
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<String> cauldron = new ArrayList<>();
        Chef chef = new Chef(cauldron);
        for (int i = 1; i < 11; i++) {
            service.submit(new Cannibal(semaphore, i, cauldron, chef));
        }
    }
}
