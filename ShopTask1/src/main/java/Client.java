import java.util.List;
import java.util.Random;

public class Client implements Runnable{

    private int id;
    private boolean isSleep;
    private final List<Client> clientList;
    private static final Random RANDOM = new Random();

    public Client(int id, List<Client> clientList) {
        this.id = id;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        System.out.println("Клиент: " + id + " зашел в магазин");
        try {
            Thread.sleep(RANDOM.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!clientList.isEmpty()) {
            isSleep = true;
        }
        synchronized (clientList) {
            clientList.add(this);
        }
    }

    public void setIsSleep(boolean b) {
        isSleep = b;
    }
}
