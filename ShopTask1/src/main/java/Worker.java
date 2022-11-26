import java.util.List;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable{

    private static int COUNTER = 0;
    private final List<Client> clientList;
    private final Semaphore semaphore;
    private static final int MAX_CLIENTS_QUANTITY = 31;
    private static final int FIRST_CLIENT_IN_QUEUE = 0;
    private static final int SERVE_CLIENT_TIME = 2000;
    private static final int WAITING_CLIENT_TIME = 1000;

    public Worker(List<Client> clientList, Semaphore semaphore) {
        this.clientList = clientList;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        while (true) {
            if (!clientList.isEmpty()) {
                try {
                    if (COUNTER == MAX_CLIENTS_QUANTITY) {
                        clientList.clear();
                        System.out.println("Рабочий день закончен");
                        return;
                    }
                    semaphore.acquire();
                    System.out.println("Обслуживаем клиента номер " + ++COUNTER);
                    clientList.get(FIRST_CLIENT_IN_QUEUE).setIsSleep(false);
                    clientList.remove(FIRST_CLIENT_IN_QUEUE);
                    semaphore.release();
                    Thread.sleep(SERVE_CLIENT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(WAITING_CLIENT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
