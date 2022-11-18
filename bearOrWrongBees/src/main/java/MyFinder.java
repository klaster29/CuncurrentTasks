import java.util.List;

public class MyFinder implements Runnable{

    List<String> forestPart;
    boolean isFound;
    int numberOfSquad;

    public MyFinder(List<String> forestPart, int i) {
        this.forestPart = forestPart;
        isFound = false;
        numberOfSquad = i;
    }

    @Override
    public void run() {
        for (String forestObject : forestPart) {
            if(forestObject.equals("bear")) {
                System.out.println("Отряд " + numberOfSquad + " - Цель найдена");
                wheelOfSufferingFromDante();
                isFound = true;
            }
        }
        if (isFound) {
            System.out.println("Отряд " + numberOfSquad + " - Отчет в улей: Медведь с подельником был обнаружен, ружье отняли и... нас больше не " +
                    "побеспокоят");
        } else {
            System.out.println("Отряд " + numberOfSquad + " - Отчет в улей: Цель не найдена, нужно дождаться отчета от других групп. " +
                    "Враг не мог уйти слишком далеко");
        }
    }

    private void wheelOfSufferingFromDante() {
        int random = (int)(Math.random() * 10);
        switch (random) {
            case 1:
                System.out.println("Отряд " + numberOfSquad + " - Пчела-священник отрекает от веры злодея и обрекает его, " +
                        "таким образом, на 1 круг ада");
            break;
            case 2:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы сговариваются и находят Вини Пуху двух страстных любовниц, " +
                        "обрекая на 2 круг ада");
            break;
            case 3:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы подкидывают Вини Пуху кучу разнообразной еды на протяжении месяца, " +
                        "чтобы тот потолстел до невозможности, обрекая его на 3 круг ада");
            break;
            case 4:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы воруют у Вини Пуха и его друзей все ценные вещи, на протяжении долгого времени, " +
                        "вынуждая Вини Пуха стать скупым, обрекая его на 4 круг ада");
            break;
            case 5:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы мешают любым начинаниям Вини Пуха, делая его злым, обрекая на 5 круг ада");
            break;
            case 6:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы долгое время рассказывают Вини Пуху о теории плоской земли, " +
                        "делая его в перспективе лжеучителем, обрекая на 6 круг ада");
            break;
            case 7:
                System.out.println("Отряд " + numberOfSquad + " - Срочнейшим образом, пчелы находят супругу для Вини Пуха, для раннего брака. " +
                        "В последствии тот становится тираном. 7 круг ада");
            break;
            case 8:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы отправляют Вини Пуха в барбершоп, чтобы тот начал нравиться медведицам. " +
                        "Обольститель, 8 круг ада");
            break;
            case 9:
                System.out.println("Отряд " + numberOfSquad + " - Пчелы уговаривают не делиться остатками меда с пятачком. 9 круг ада");
            break;
            default:
                System.out.println("Отряд " + numberOfSquad + " - открыт новый вид страданий и реализован");
            break;
        }
    }
}
