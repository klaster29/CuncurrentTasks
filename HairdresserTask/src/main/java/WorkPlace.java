import java.util.Random;

public class WorkPlace {

    HairDresser hairDresser;

    public WorkPlace(HairDresser hairDresser) {
        this.hairDresser = hairDresser;
    }

    public void execute()  {
        for (int i = 1; i < 11; i++) {
            hairDresser.cutClient(new Client(i));
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
