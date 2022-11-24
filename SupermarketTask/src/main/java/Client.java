import java.util.List;

public class Client implements Runnable{

    private final int id;
    private final Worker worker;
    private final List<Client> clients;

    public Client(int id, Worker worker, List<Client> clients) {
        this.id = id;
        this.worker = worker;
        this.clients = clients;
    }

    @Override
    public void run() {
        System.out.println("клиент: " + id + " занимается покупками");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("клиент: " + id + " стал в очередь");
        synchronized (clients) {
            clients.add(this);
        }
        worker.wakeUp();
    }
}
