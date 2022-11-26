import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hospital {

    private final List<Client> surgeonList;
    private final List<Client> therapistList;
    private final List<Client> dentistList;
    private final List<Client> clientList;

    private final ExecutorService doctors;
    private final ExecutorService surgeon;
    private final ExecutorService therapist;
    private final ExecutorService dentist;
    private final ExecutorService clients;

    public Hospital() {
        surgeonList = new ArrayList<>();
        therapistList = new ArrayList<>();
        dentistList = new ArrayList<>();
        clientList = new ArrayList<>();
        doctors = Executors.newFixedThreadPool(2);
        surgeon = Executors.newSingleThreadExecutor();
        therapist = Executors.newSingleThreadExecutor();
        dentist = Executors.newSingleThreadExecutor();
        clients = Executors.newFixedThreadPool(3);
    }

    public void startWorkDay() {
        startDoctorsJob();
        startSpecialistJob();
        clientComing();
    }

    public void shutdownAllExecutors() {
        doctors.shutdown();
        surgeon.shutdown();
        therapist.shutdown();
        dentist.shutdown();
        clients.shutdown();
    }

    private void startDoctorsJob() {
        doctors.execute(new Doctor(surgeonList, therapistList, dentistList, clientList));
        doctors.execute(new Doctor(surgeonList, therapistList, dentistList, clientList));
    }

    private void startSpecialistJob() {
        surgeon.execute(new Specialist(surgeonList));
        therapist.execute(new Specialist(therapistList));
        dentist.execute(new Specialist(dentistList));
    }

    private void clientComing () {
        for (int i = 1; i < 31; i++) {
            clients.execute(new Client(i, clientList));
        }
    }
}
