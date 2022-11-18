import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HairDresser{

    ExecutorService hairDresser = Executors.newSingleThreadExecutor();
    int maxQuantityClients = 10;
    boolean isSleep = true;

    public void cutClient(Client client)  {
        System.out.println("Начинаю стрижку клиента " + client.id);
        isSleep = false;

        Future<?> future = hairDresser.submit(client);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (client.id == maxQuantityClients) {
            hairDresser.shutdown();
            System.out.println("Заканчиваю рабочий день");
            return;
        }
        isSleep = true;
    }
}
