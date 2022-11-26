
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int WORKING_HOURS = 34000;
        Hospital hospital = new Hospital();

        hospital.startWorkDay();
        hospital.shutdownAllExecutors();

        Thread.sleep(WORKING_HOURS);
        System.out.println("Госпиталь закрывается");
    }
}
