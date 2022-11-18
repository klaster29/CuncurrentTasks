public class Worker implements Runnable{

    final HoneyPot pot;

    public Worker(HoneyPot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        while (true) {
            if (pot.isFull) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (!pot.isFull) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (pot) {
                    for (int i = 0; i < 10; i++) {
                        if (pot.honeyPot[i] == null) {
                            pot.honeyPot[i] = "honey";
                            if(pot.honeyPot[9] != null) {
                                pot.isFull = true;
                                System.out.println("Бачонок с медом заполнился");
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
