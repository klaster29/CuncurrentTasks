import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Hotel {

    private final List<Client> clients;
    private final List<Integer> rooms;
    private final int workerQuantity;
    private final int clientsQuantity;
    private static final int HOURS_IN_DAY = 24;
    private static final int MAX_FILLED_ROOMS = 30;
    private final int waitingTermination;
    private final ExecutorService serveClient = Executors.newSingleThreadExecutor();
    private final ExecutorService serveQueue = Executors.newFixedThreadPool(2);
    private final ExecutorService releaseRoomer = Executors.newSingleThreadExecutor();
    private final Semaphore clientSemaphore;

    public Hotel() {
        this.clients = new ArrayList<>();
        this.rooms = new ArrayList<>(MAX_FILLED_ROOMS);
        workerQuantity = 2;
        clientsQuantity = 100;
        waitingTermination = 1000;
        clientSemaphore = new Semaphore(1);
    }

    public static void startApp() {
        Hotel hotel = new Hotel();
        hotel.startWorkCircle();
        hotel.stopWorkCircle();
    }

    private void startWorkCircle() {
        System.out.println("Отель открывается");
        startEmployees();
        startReleaseRoomer();
        clientsComing();
    }

    private void stopWorkCircle() {
        serveClient.shutdown();
        serveQueue.shutdown();
        releaseRoomer.shutdown();

        try {
            while (!(serveClient.awaitTermination(HOURS_IN_DAY, TimeUnit.HOURS) &&
                    serveQueue.awaitTermination(HOURS_IN_DAY, TimeUnit.HOURS) &&
                    releaseRoomer.awaitTermination(HOURS_IN_DAY, TimeUnit.HOURS))) {
                Thread.sleep(waitingTermination);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Отель закрывается");
    }

    private void startEmployees() {
        for (int i = 1; i <= workerQuantity; i++) {
            serveQueue.execute(new Worker(clients, rooms, clientSemaphore));
        }
    }
    private void startReleaseRoomer() {
        releaseRoomer.execute(new ReleaseWorker(rooms));
    }

    private void clientsComing() {
        for(int i = 1; i <= clientsQuantity; i++) {
            serveClient.execute(new Client(i, clients, clientSemaphore));
        }
    }
}
