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

/**
 * HashMap: HashMap to implementacja mapy, która przechowuje pary klucz-wartość. Pozwala na efektywne wyszukiwanie wartości na podstawie klucza.
 * <p>
 * HashSet: HashSet to kolekcja typu set, która przechowuje unikalne elementy. Nie pozwala na duplikaty.
 * <p>
 * LinkedHashSet: LinkedHashSet to rodzaj kolekcji typu set, który zachowuje kolejność elementów, w której zostały one dodane.
 * <p>
 * TreeMap: TreeMap to implementacja mapy, która przechowuje pary klucz-wartość w uporządkowanym porządku na podstawie klucza.
 * <p>
 * TreeSet: TreeSet to kolekcja typu set, która przechowuje elementy w uporządkowanym porządku.
 * <p>
 * Queue: Queue to interfejs reprezentujący kolekcję, która działa na zasadzie kolejki (FIFO - First-In-First-Out). LinkedList jest często używany jako implementacja kolejki.
 * <p>
 * Hashtable: Hashtable to implementacja mapy, podobna do HashMap, ale jest w pełni zsynchronizowana.
 * <p>
 * ConcurrentHashMap: ConcurrentHashMap to implementacja mapy, która jest zoptymalizowana pod kątem współbieżności i może być używana w wielowątkowych środowiskach.
 * <p>
 * LinkedHashMap: LinkedHashMap to implementacja mapy, która zachowuje kolejność dodawania elementów.
 */
