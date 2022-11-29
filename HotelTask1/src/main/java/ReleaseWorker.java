import java.util.List;

public class ReleaseWorker implements Runnable{

    private final List<Integer> rooms;
    private int counter;
    private final int maxClients;
    private final int firstClientToRelease;
    private final int clientsLifeCircle;

    public ReleaseWorker(List<Integer> rooms) {
        this.rooms = rooms;
        counter = 1;
        maxClients = 100;
        firstClientToRelease = 0;
        clientsLifeCircle = 1000;
    }

    @Override
    public void run() {
        while (counter < maxClients) {
            if (!rooms.isEmpty()) {
                ++counter;
                System.out.println("Отель покинул клиент " + rooms.get(firstClientToRelease));
                rooms.remove(firstClientToRelease);
            }
            try {
                Thread.sleep(clientsLifeCircle);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Отель покинуло " + counter + " клиентов. Рабочий цикл закончен");
    }
}
