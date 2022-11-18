public class Client implements Runnable{
    int id;
    boolean isWait;
    boolean isSleep;

    public Client(int id) {
        this.id = id;
        isWait = true;
    }

    @Override
    public void run() {
        isWait = false;
        isSleep = true;
        System.out.println("Начинаем стрижку");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Клиент подстрижен, будим его");
        isSleep = false;
        System.out.println("Клиент благодарит вас и уходит");
        if (!isWait && !isSleep) {
            System.out.println("Клиент " + id + " ушёл");
        }
    }
}
