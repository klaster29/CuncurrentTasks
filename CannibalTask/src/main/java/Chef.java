import java.util.List;

public class Chef {

    List<String> cauldron;

    public Chef(List<String> cauldron) {
        this.cauldron = cauldron;
    }

    public void fillСauldron() {
        for (int i = 0; i < 10; i++) {
            cauldron.add("meat");
        }
        System.out.println("Наполнил котел мясом");
    }
}
