import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6; i++) {
            service.submit(new Philosopher(semaphore, i));
        }
    }
}
