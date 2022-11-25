import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        List<Client> clientList = new ArrayList<>();
        Semaphore semaphore = new Semaphore(1);
        List<Worker> workerList = List.of(
                new Worker(clientList, semaphore), new Worker(clientList, semaphore), new Worker(clientList, semaphore));
        Shop shop = new Shop(
                Executors.newFixedThreadPool(3), workerList, Executors.newFixedThreadPool(5));
        shop.startWork();
        for (int i = 1; i < 51; i++) {
            shop.startClientBehavior(new Client(i, clientList));
        }

        shop.stopExecutorServices();
        System.out.println("Завершили рабочий день из Main");
    }
}
