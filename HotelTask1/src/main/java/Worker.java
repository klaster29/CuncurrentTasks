import java.util.List;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable{

    private final List<Client> clients;
    private final List<Integer> rooms;
    private final Semaphore semaphore;
    private static int COUNTER = 1;
    private static final int MAX_CLIENTS_QUANTITY = 100;
    private static final int MAX_ROOMS = 30;
    private static final int SERVE_CLIENT = 1000;
    private static final int FIRST_CLIENT_IN_QUEUE = 0;

    public Worker(List<Client> clients, List<Integer> rooms, Semaphore semaphore) {
        this.clients = clients;
        this.rooms = rooms;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (COUNTER < MAX_CLIENTS_QUANTITY) {
            try {
                semaphore.acquire();
                if (!clients.isEmpty()) {
                    if (!(rooms.size() == MAX_ROOMS)) {
                        ++COUNTER;
                        int clientId = clients.get(FIRST_CLIENT_IN_QUEUE).getId();
                        rooms.add(clientId);
                        System.out.println("Клиент " + clientId + " заселен в отель.");
                        clients.remove(FIRST_CLIENT_IN_QUEUE);
                    }
                }
                semaphore.release();
                Thread.sleep(SERVE_CLIENT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Рабочий цикл сотрудников завершен. Обслужено клиентов: " + COUNTER);
    }
}
