import java.util.List;

public class MyProducer extends Thread{

    List<Integer> warehouse;
    List<Loot> looted;

    public MyProducer(List<Integer> warehouse, List<Loot> looted) {
        this.warehouse = warehouse;
        this.looted = looted;
    }

    @Override
    public void run() {
        int goodsQuantity = 10;
        for (int i = 0; i < goodsQuantity; i++) {
            looted.add(new Loot(warehouse.get(i)));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
