import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{

    Semaphore semaphore;
    int id;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {

                semaphore.acquire();
                System.out.println("Philosopher: " + id + " start eating");
                Thread.sleep(1000);
                System.out.println("Philosopher: " + id + " thinking");
                semaphore.release();
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
