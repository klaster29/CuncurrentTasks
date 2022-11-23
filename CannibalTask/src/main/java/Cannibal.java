import java.util.List;
import java.util.concurrent.Semaphore;

public class Cannibal implements Runnable{

    Semaphore s;
    int id;
    final List<String> food;
    Chef chef;

    public Cannibal(Semaphore s, int id, List<String> food, Chef chef) {
        this.s = s;
        this.id = id;
        this.food = food;
        this.chef = chef;
    }

    @Override
    public void run() {
        while(true) {
            try {

                synchronized (food) {
                    if (food.isEmpty()) {
                        System.out.println("котел пуст");
                        chef.fillСauldron();
                    }
                    s.acquire();
                    food.remove(0);
                    System.out.println("поел канибал: " + id);
                }
                s.release();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
