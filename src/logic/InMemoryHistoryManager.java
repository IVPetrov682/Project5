package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class InMemoryHistoryManager implements HistoryManager{

    static myLinkedList<Task> history = new myLinkedList<>();
    static HashMap<Integer, Node> nodeHistory = new HashMap<>();

    @Override
    public int sizeHistory() {
        return history.size();
    }

    public InMemoryHistoryManager() {
    }

    @Override
    public void add(Task task){

        int iD = task.getCodeTask();
        if (nodeHistory.containsKey(iD)) {
            Task lastTaskHistory = history.getLast();

            System.out.println("lastTaskHistory  " + lastTaskHistory.getCodeTask() + ". " + lastTaskHistory.getCodeTaskName());
            System.out.println("task " + task.getCodeTask() + ". " + task.getCodeTaskName());
            //// тесты

            if (lastTaskHistory.getCodeTask() != task.getCodeTask()) { //   проверяем наличие по ID
                System.out.println("Заменяем Элементы");
                Node del = nodeHistory.get(iD); // получили значение
                history.deleteNode(del); // удаляем нод из самодельного списка через node
                nodeHistory.remove(iD); // удаляем строку в таблице в истории задач
                nodeHistory.put(iD, history.linkLast(task)); // Дабавляем новое значение
            } else {
                System.out.println("Уже последнее значение в истории");
            }
        } else {
            System.out.println("Добавляем значение");
            nodeHistory.put(iD, history.linkLast(task));
        }

        ///////////////тесты
    //    System.out.println("Размер списка № " + history.size()); //!!!!!!!!!!!!!!!!!!!!!//////////////////////////////////////////////////////
    //    System.out.println("Размер таблицы № " + nodeHistory.size()); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//////////////////////////////////////////////
    //    Task x = history.getFirst();
    //    Task y = history.getLast();
    //    System.out.println("Голова. Задача № " + x.getCodeTask() + ". " + x.getCodeTaskName());
    //    System.out.println("Хвост. Задача № " + y.getCodeTask() + ". " + y.getCodeTaskName());
        //////////////////// конец теста
    }

    @Override
    public List<Task> getHistory() {
        return history.asList(); // Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
    }
    @Override
    public void removeTaskHistory(Task task) {
        int iD = task.getCodeTask(); // получили ключ
        Node del = nodeHistory.get(iD); // получили значение

        history.deleteNode(del); // удаляем нод из самодельного списка через node
        nodeHistory.remove(iD); // удаляем строку в таблице в истории задач

        System.out.println("Размер списка после ревув " + history.size());
        System.out.println("Размер таблицы после ревув  " + nodeHistory.size());


    }

    public static class myLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;
        private int size = 1;
        private Node<T> next;
        private Node<T> prev;

        public List<T> asList () {
           List<T> result = new ArrayList<>();
           Node<T> curr = head;
           while (curr != null) {
               result.add(curr.data);
               curr = curr.next;
           }
           return result;
        }

        void deleteNode(Node del) {  ///Node del не работает

            // Base case
            if (head == null) {
                System.out.println("Cписок пуст");
                return;
            }

            if (head == del) {
                head = del.next;
                size--;
            }

            if (del.next != null) {
                del.next.prev = del.prev;
            }
            if (del.prev != null) {
                del.prev.next = del.next;
                size--;
            }
        }

        public Node linkLast(T element) {
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(tail, element, null);
            tail = newNode;
            if (!(oldTail == null)){
                oldTail.next = newNode;
                size++;
            } else {
                head = newNode;
            }
            return newNode;
        }

        public T getLast() {
            final Node<T> curTail = tail;
            if (curTail == null)
                throw new NoSuchElementException();
            return tail.data;
        }

        public int size() {
            return this.size;
        }
    }
}
