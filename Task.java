import java.util.HashMap;

public class Task {
    HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача

    static Counter number = new Counter(); // Создали объект класса счетчика
    public Task(int codeTask, String codeTaskName) {

        // с второстепенными задачами
        numberTask = new HashMap<>();
        number.increment(); // увеличили счетчик на 1
        numberTask.put(number.getCount(), new ManagerStatus("Отдохнуть", Status.INPROGRESS)); // сохранили в таблицу
        System.out.println("На счетчике " + number.getCount()); // показали счетчик на 1
        // без второстепенных задач
        number.increment(); // увеличили счетчик на 1
        numberTask.put(number.getCount(), new ManagerStatus("Посмотреть сериал звезды в Африке", Status.INPROGRESS)); // сохранили в таблицу
        System.out.println("На счетчике " + number.getCount()); // показали счетчик

        number.increment(); // увеличили счетчик на 3
        numberTask.put(5, new ManagerStatus("Попить кофе", Status.INPROGRESS)); // сохранили в таблицу // вызвали ошибку. Задачи 1, 2, 4. По счетчику 3
        System.out.println("На счетчике " + number.getCount()); // показали счетчик на 1

    }

}
