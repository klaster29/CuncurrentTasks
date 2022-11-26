import java.util.List;
import java.util.concurrent.Semaphore;

public class Specialist implements Runnable{

    private final List<Client> clientList;
    private static Semaphore semaphore;

    private final static int SERVE_CLIENT = 3000;
    private final static int FIRST_CLIENT_IN_QUEUE = 0;
    private final static int WAITING_CLIENT = 1000;
    private final static int MAX_CLIENTS_QUANTITY = 27;
    private static int COUNTER = 0;

    public Specialist(List<Client> clientList) {
        this.clientList = clientList;
        semaphore = new Semaphore(1);
    }

    @Override
    public void run() {
        System.out.println("Начало работы специалиста");
        while (true) {
            if (COUNTER > MAX_CLIENTS_QUANTITY){
                System.out.println("конец рабочего дня специалиста");
                return;
            }

            if (!clientList.isEmpty()) {
                try {
                    Thread.sleep(SERVE_CLIENT);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++COUNTER;
                    System.out.println("латаем клиента: " + COUNTER);
                    clientList.remove(FIRST_CLIENT_IN_QUEUE);
                semaphore.release();
            } else {
                try {
                    Thread.sleep(WAITING_CLIENT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
