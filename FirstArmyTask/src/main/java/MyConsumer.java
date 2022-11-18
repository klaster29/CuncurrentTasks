import java.util.List;
import java.util.Set;

public class MyConsumer extends Thread{

    Set<Loot> truck;
    List<Loot> looted;

    public MyConsumer(Set<Loot> truck, List<Loot> looted) {
        this.truck = truck;
        this.looted = looted;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(700);
                looted.stream()
                        .filter(Loot::isValued)
                        .forEach(loot -> truck.add(loot));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
