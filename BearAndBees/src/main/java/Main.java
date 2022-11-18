import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String[] pot = new String[10];
        HoneyPot honeyPot = new HoneyPot(pot);
        Hive hive = new Hive(Executors.newFixedThreadPool(4));
        hive.execute(new Worker(honeyPot), new Worker(honeyPot), new Worker(honeyPot));
        hive.executeBear(new Bear(honeyPot));
    }
}
