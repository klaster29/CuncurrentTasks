import java.util.List;

public class Worker implements Runnable{

    private boolean isSleep;
    private final List<Client> clients;
    int counter = 0;

    public Worker(List<Client> clients) {
        isSleep = false;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (!clients.isEmpty()) {
                    synchronized (clients) {
                        ++counter;
                        clients.remove(0);
                        System.out.println("Обслуживаем клиента " + counter);
                        if(counter == 30) {
                            return;
                        }
                    }
                    Thread.sleep(1000);
                }
                isSleep = true;
                System.out.println("Кассир спит");
                while (clients.isEmpty() && isSleep) {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void wakeUp() {
        isSleep = false;
    }
}
