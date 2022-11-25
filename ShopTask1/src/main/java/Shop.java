import java.util.List;
import java.util.concurrent.ExecutorService;

public class Shop {

    private final ExecutorService workerService;
    private final List<Worker> workerList;
    private final ExecutorService clientService;

    public Shop(ExecutorService workerService, List<Worker> workerList, ExecutorService clientService) {
        this.workerService = workerService;
        this.workerList = workerList;
        this.clientService = clientService;
    }

    public void startWork() {
        for (Worker worker : workerList) {
            workerService.execute(worker);
        }
    }

    public void startClientBehavior(Client client) {
        clientService.execute(client);
    }

    public void stopExecutorServices() {
        workerService.shutdown();
        clientService.shutdown();
    }
}
