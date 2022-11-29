import java.util.List;
import java.util.concurrent.Semaphore;

public class Client implements Runnable{

    private final int id;
    private final List<Client> clients;
    private final Semaphore semaphore;
    public static int WAITING = 1000;

    public Client(int id, List<Client> clients, Semaphore semaphore) {
        this.id = id;
        this.clients = clients;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(WAITING);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Клиент " + id + " ожидает заселения в отель");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clients.add(this);
        semaphore.release();
    }

    public int getId() {
        return id;
    }
}
