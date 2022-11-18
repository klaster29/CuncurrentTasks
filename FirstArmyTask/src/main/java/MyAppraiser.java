import java.util.List;

public class MyAppraiser extends Thread{

    List<Loot> looted;

    public MyAppraiser(List<Loot> looted) {
        this.looted = looted;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                looted.forEach(loot -> loot.setCost(loot.getNumberOfLoot()));

                for (Loot loot : looted) {
                    if (loot.getCost() != 0 && !loot.isValued()) {
                        loot.setValued(true);
                    }
                }
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
