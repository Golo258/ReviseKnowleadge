package org.example.Revise.CollectionsKnow.ListOnes;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Others {

    /**
     * LinkedList: LinkedList
     * to struktura danych oparta na liście podwójnie wiązanej.
     * Jest wydajna w operacjach na początku i końcu listy.
     * lista połączonych ze sobą węzłów.
     * kazdy wezel zawiera dane i wskaznik do nastepnego wezla w liscie
     */

    public void showLinkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        // adding to beginning
        linkedList.addFirst("One");
        linkedList.addFirst("Two");
        // adding to tail
        linkedList.addLast("Three");
        linkedList.addLast("Four");
        for (String element : linkedList) {
            System.out.println(element);
        }
    }

    /**
     * Vector- podobnie jak ArrayList, ale jest synchronizowana,
     * Jest bezpieczna w przypadku wielu wątków
     * Blokuje dostep do swoich metod
     */
//    współdzielenie listy miedzy wieloma wątkami
    //TODO to be learned later when Threads will be learned
    public void showVector() {
        Vector<Integer> vector = new Vector<>();
        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Stack - czyli LIFO - last in first out
     * ostatni wchodzi pierwszy wychodzi
     * pierwszy wchodzi ostatni wychodzi
     * Do czego Obsługa nawigacji w aplikacjach webowych: Stack może przechowywać historię nawigacji użytkownika w przeglądarce.
     */
//    DO analizy wyrażeń matematycznych
    public void showStackColl() {
        String expression1 = "{[()]}";
        Stack<Character> stack = new Stack<>();
        boolean isBalanced = false;
        for (char sign : expression1.toCharArray()) {
            if (sign == '(' || sign == '[' || sign == '{') {
                stack.push(sign);
            } else if (sign == ')' || sign == ']' || sign == '}') {
                if (stack.isEmpty()) {
                    isBalanced = false;
                }

                char top = stack.pop();

                if ((sign == ')' && top != '(') || (sign == ']' && top != '[') || (sign == '}' && top != '{')) {
                    isBalanced = false;
                }
            }
        }
        System.out.println("Is Balanced: " + isBalanced);
    }

    public void ShowCopyOnWriteArrayListExample() {
//        TODO later not threads understanding
    }

    /**
     * AbstractList - klasa abstracyjna w jAVIE
     * implementuje funckjonalsc interfeujsu List
     * podstawa do tworzenia implementacji listy
     * get ( ) set () add () remove() ..
     */
    public void showAbstractList() {
        Character[] signs = {'C', 'B', 'A', 'J', 'Q'};
        ImmutableArrayList<Character> chars = new ImmutableArrayList<>(signs);
        System.out.println(chars.get(1));
    }

    /**
        FIFO

     */
    public void showLinkedQueue(){
        Queue<String> kolejka = new LinkedList<>();
        kolejka.offer("First"); // enqueue
        kolejka.offer("ThIRD");
        kolejka.offer("Second");
        System.out.println(kolejka);
        String elem = kolejka.poll();
        System.out.println(elem + " has been removed");
        System.out.println("Actualised queue: " + kolejka);
    }

    /**
     ArrayDeque -> oparta na tablicy
     efektywna gdy chcemy dodawac/ usuwac z poczatka i konca kolejki
     przechwouje w kolejnosci jakiej dodajemy
     */
    public void showArryayDeque(){
        Deque<String> stringDeque = new ArrayDeque<>();
        stringDeque.add("First");
        stringDeque.add("352abc");
        stringDeque.add("sfasf");
        System.out.println(stringDeque + " : " + stringDeque.poll());
    }
    /**
     PiorityQueue -> kolejka priorytetowa
     elementy sa przechowywane w porzadku rosnacy/malejacym lub od priorytetu
     */
    public void showPriorityQueue(){
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(new TaskComparator());
        taskQueue.add( new Task(1, "Example 1", "dzisiaj"));
        taskQueue.add( new Task(5, "Example 2", "pojutrze"));
        taskQueue.add( new Task(5, "Example 3", "jutro"));
        taskQueue.add( new Task(2, "Example 4", "nazajutrz"));
        System.out.println(taskQueue);
    }
}
class Task{
    private int priority;
    private String name;
    private String term;

    public Task(int priority, String name, String term) {
        this.priority = priority;
        this.name = name;
        this.term = term;
    }

    public int getPriority() {
        return priority;
    }

    public String getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", name='" + name + '\'' +
                ", term='" + term + '\'' +
                '}';
    }
}
class TaskComparator implements Comparator<Task>{
    @Override
    public int compare(Task one, Task another) {
        if(one.getPriority() != another.getPriority()){
            return one.getPriority() - another.getPriority();
        }
        else{
            return one.getTerm().length() - another.getTerm().length();
        }
    }
}
/**
 * Niemutowalność-- immutability
 * odnosi się do cechy obiektów -, które nie mogą byćmodyfikowane po ich utworzeniu
 * ich stan nie moze sie zmienic
 * --jak
 * deklaracja pola jako final
 */
class ImmutableArrayList<Element> extends AbstractList<Element> {
    private final Element[] elements;

    public ImmutableArrayList(Element[] elems) {
        this.elements = elems;
    }

    @Override
    public Element get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        } else {
            return elements[index];
        }
    }

    @Override
    public int size() {
        return elements.length;
    }

}

class ImmutableClass {
    private final int notChangedField;

    public ImmutableClass(int notChangedField) {
        this.notChangedField = notChangedField;
    }

    public int getImmutableField() {
        return this.notChangedField;
    } // there is not setters
}

