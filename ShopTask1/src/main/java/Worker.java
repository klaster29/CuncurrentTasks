import java.util.List;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable{

    public static int COUNTER = 0;
    final List<Client> clientList;
    Semaphore semaphore;
    private static final int MAXCLIENTSQUANTITY = 30;
    private static final int LASTCLIENTINQUEUE = 27;

    public Worker(List<Client> clientList, Semaphore semaphore) {
        this.clientList = clientList;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        while (true) {
            if (!clientList.isEmpty()) {
                try {
                    semaphore.acquire();
                    System.out.println("Обслуживаем клиента номер " + ++COUNTER);
                    clientList.get(0).setIsSleep(false);
                    clientList.remove(0);
                    semaphore.release();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (COUNTER == MAXCLIENTSQUANTITY) {
                clientList.clear();
                System.out.println("Рабочий день закончен");
            }
            if (COUNTER > LASTCLIENTINQUEUE) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
