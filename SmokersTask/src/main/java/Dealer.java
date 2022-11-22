import java.util.List;

public class Dealer implements Runnable {

    List<String> component;

    public Dealer(List<String> component) {
        this.component = component;
    }

    @Override
    public void run() {
        for(int i = 0; i < 4; i++) {
            while (!component.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (i == 3) {
                i = 0;
            }

            if (i == 0){
                component.add("спички");
                component.add("бумага");
                System.out.println("Добавлены спички и бумага");
                continue;
            }
            if (i == 1) {
                component.add("табак");
                component.add("бумага");
                System.out.println("Добавлены табак и бумага");
                continue;
            }
            if (i == 2) {
                component.add("табак");
                component.add("спички");
                System.out.println("Добавлены табак и спички");
            }
        }
    }
}
