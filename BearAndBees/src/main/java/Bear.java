import java.util.Arrays;

public class Bear implements Runnable{

    final HoneyPot pot;

    public Bear(HoneyPot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pot) {
                if (pot.isFull) {
                    Arrays.fill(pot.honeyPot, null);
                    System.out.println("Мёд съеден, несите ещё");
                    pot.isFull = false;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
