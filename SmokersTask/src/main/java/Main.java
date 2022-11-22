import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("спички", "бумага", "табак");
        List<String> components = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new Dealer(components));
        for (int i = 0; i < 3; i++) {
            service.submit(new Smoker(list.get(i), components));
        }
    }
}
