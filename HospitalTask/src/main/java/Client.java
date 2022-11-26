import java.util.List;
import java.util.concurrent.Semaphore;

public class Client implements Runnable{

    private final int id;
    private final List<Client> clientList;
    private static Semaphore semaphore;

    private static final int GETTING_IN_LINE = 100;

    public Client(int id, List<Client> clientList) {
        this.id = id;
        this.clientList = clientList;
        semaphore = new Semaphore(1);
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println("клиент " + id + " пришел в больницу и встал в очередь");
        try {
            Thread.sleep(GETTING_IN_LINE);
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientList.add(this);
        semaphore.release();
    }
}
