import java.util.List;
import java.util.concurrent.Semaphore;

public class Doctor implements Runnable{

    private final List<Client> surgeonList;
    private final List<Client> therapistList;
    private final List<Client> dentistList;
    private final List<Client> clientList;

    private static int COUNTER = 0;
    private static Semaphore semaphore;
    private final static int SERVE_CLIENT = 1000;
    private final static int WAITING_CLIENT = 1000;
    private final static int MAX_CLIENTS_QUANTITY = 28;
    private final static int FIRST_CLIENT_IN_QUEUE = 0;
    private final static int SPECIALIST_QUANTITY = 3;
    private final static int SURGEON = 0;
    private final static int THERAPIST = 1;
    private final static int DENTIST = 2;


    public Doctor(List<Client> surgeonList, List<Client> therapistList, List<Client> dentistList,
                  List<Client> clientList) {
        this.surgeonList = surgeonList;
        this.therapistList = therapistList;
        this.dentistList = dentistList;
        this.clientList = clientList;
        semaphore = new Semaphore(1);
    }

    @Override
    public void run() {
        System.out.println("Начало работы доктора");
        while (true) {
            if (COUNTER > MAX_CLIENTS_QUANTITY) {
                System.out.println("конец рабочего дня доктора");
                return;
            }

            if (!clientList.isEmpty()) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int clientId = clientList.get(FIRST_CLIENT_IN_QUEUE).getId();
                ++COUNTER;

                if (clientId % SPECIALIST_QUANTITY == SURGEON) {
                    surgeonList.add(clientList.get(FIRST_CLIENT_IN_QUEUE));
                    clientList.remove(FIRST_CLIENT_IN_QUEUE);
                    System.out.println("Клиент " + clientId + " к хирургу");
                } else if (clientId % SPECIALIST_QUANTITY == THERAPIST) {
                    therapistList.add(clientList.get(FIRST_CLIENT_IN_QUEUE));
                    clientList.remove(FIRST_CLIENT_IN_QUEUE);
                    System.out.println("Клиент " + clientId + " к терапевту");
                } else if (clientId % SPECIALIST_QUANTITY == DENTIST){
                    dentistList.add(clientList.get(FIRST_CLIENT_IN_QUEUE));
                    clientList.remove(FIRST_CLIENT_IN_QUEUE);
                    System.out.println("Клиент " + clientId + " к дантисту");
                }
                semaphore.release();
                try {
                    Thread.sleep(SERVE_CLIENT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(WAITING_CLIENT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
