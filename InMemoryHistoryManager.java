import java.util.ArrayList;
import java.util.List;

class InMemoryHistoryManager implements HistoryManager{
    private int codeTask;
    private String codeTaskName;

    List<Task> history = new ArrayList(2);


    @Override
    public void add(Task task){
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;         // Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
    }
}
