package logic;

import java.util.*;

class InMemoryHistoryManager implements HistoryManager{


    myLinkedList<Task> history = new myLinkedList<>();
    HashMap<Integer, Node> nodeHistory = new HashMap<>();

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
            history.removeNode(iD);
        }
        nodeHistory.put(iD, history.linkLast(task));

        ///////////////тесты
        System.out.println("Размер списка № " + history.size()); //!!!!!!!!!!!!!!!!!!!!!//////////////////////////////////////////////////////
        System.out.println("Размер таблицы № " + nodeHistory.size()); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//////////////////////////////////////////////
        Task x = history.getFirst();
        Task y = history.getLast();
        System.out.println("Голова. Задача № " + x.getCodeTask() + ". " + x.getCodeTaskName());
        System.out.println("Хвост. Задача № " + y.getCodeTask() + ". " + y.getCodeTaskName());
        //////////////////// конец теста
    }


    @Override
    public List<Task> getHistory() {
      //  ArrayList<Task> reHistory = new ArrayList((Collection) history);

        ArrayList<Task> reHistory = new ArrayList<>(history);
        return reHistory; // Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
    }

    public class myLinkedList<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size = 1;

        public void addFirst(T element) {
            final Node<T> oldHead = head;
            final Node<T> newNode = new Node<>(null, element, oldHead);
            head = newNode;
            if (oldHead == null)
                tail = newNode;
            else
                oldHead.prev = newNode;
            size++;
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

        public T getFirst() {
            final Node<T> curHead = head;
            if (curHead == null)
                throw new NoSuchElementException();
            return head.data;
        }

        public T getLast() {
            // Реализуйте метод
            final Node<T> curTail = tail;
            if (curTail == null)
                throw new NoSuchElementException();
            return tail.data;
        }

        public int size() {
            return this.size;
        }


        public void removeNode(int iD) {
            size--;
        }
    }
}

