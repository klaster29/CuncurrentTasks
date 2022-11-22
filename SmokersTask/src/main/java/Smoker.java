import java.util.List;

public class Smoker implements Runnable{

    String component;
    List<String> components;

    public Smoker(String component, List<String> components) {
        this.component = component;
        this.components = components;
    }

    @Override
    public void run() {
        while (true) {
            if (!components.isEmpty() && !components.contains(component)) {
                System.out.println("Делаем самокрутку");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Курим");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Докурил");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                components.clear();
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
