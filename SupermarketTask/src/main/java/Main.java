import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long workHours = 30000L;
        int clientQuantity = 31;
        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorService serviceForClients = Executors.newFixedThreadPool(5);
        List<Client> clients = new ArrayList<>();
        Worker worker = new Worker(clients);
        Supermarket supermarket = new Supermarket(worker, service, serviceForClients);

        System.out.println("Начинаем рабочий день");
        supermarket.serveClient();
        for (int i = 1; i < clientQuantity; i++) {
            supermarket.comeClient(new Client(i, worker, clients));
            Thread.sleep(500);
        }


        try {
            Thread.sleep(workHours);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Закончили рабочий день");

        service.shutdown();
        serviceForClients.shutdown();
    }
}
