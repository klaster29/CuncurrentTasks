import java.util.concurrent.ExecutorService;

public class Hive {

    ExecutorService hive;

    public Hive(ExecutorService hive) {
        this.hive = hive;
    }

    public void execute(Worker worker1, Worker worker2, Worker worker3) {
        hive.submit(worker1);
        hive.submit(worker2);
        hive.submit(worker3);
    }
    public void executeBear(Bear bear) {
        hive.submit(bear);
    }
}
