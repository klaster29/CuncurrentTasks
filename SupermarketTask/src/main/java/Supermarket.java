import java.util.concurrent.ExecutorService;

public class Supermarket {
    private final Worker worker;
    private final ExecutorService workers;
    private final ExecutorService clientBehavior;

    public Supermarket(Worker worker, ExecutorService workers, ExecutorService clientBehavior) {
        this.worker = worker;
        this.workers = workers;
        this.clientBehavior = clientBehavior;
    }

    public void comeClient(Client client) {
        clientBehavior.submit(client);
    }

    public void serveClient() {
        for (int i = 0; i < 2; i++) {
            workers.submit(worker);
        }
    }
}
