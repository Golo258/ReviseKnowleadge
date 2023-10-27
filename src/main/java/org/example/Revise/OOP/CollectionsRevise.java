package org.example.Revise.OOP;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CollectionsRevise {

    /**
     * ArrayList: ArrayList to dynamiczna tablica,
     * która pozwala na przechowywanie i zarządzanie danymi w formie listy.
     * Jest to jedna z najczęściej używanych kolekcji w Javie.
     */
    public void showEachCollections() {
        ListOnes lists = new ListOnes();
        showCollection(lists.collArrayList());
        Execution.cleanOutput();
    }

    static <Type> void showCollection(Collection<Type> coll) {
        for (Type collElement : coll) {
            System.out.print(collElement + " \t");
        }
        coll.clear();
    }
}

class ListOnes {
    public ArrayList collArrayList() {
        List<String> listExtend = List.of("526", "anka", "2");
        List<String> list = new ArrayList<>();
        list.add("123");
        list.addAll(listExtend);
        System.out.println(list);
        String element = list.get(list.size() - 1);
        System.out.println(String.format("List element: %s", element));
        if (!list.get(2).contains("anka") && !list.isEmpty()) {
            list.set(2, list.get(2).toUpperCase());
        } else {
            list.set(2, "No i fajnie");
            list.remove("12345");
        }
        ArrayList<FunnyStudent> funnyStudents = new ArrayList<>(
                Arrays.asList(
                        new FunnyStudent(12, "bogdan", "obozowa"),
                        new FunnyStudent(51, "kok", "pastelowa"),
                        new FunnyStudent(2, "marcin123", "zaułek")
                )
        );
        sortingArrayList((ArrayList) list, funnyStudents);
        filteringArrays(funnyStudents);
        // removing duplicates
        Set<String> set = new HashSet<>(list);
        ArrayList<String> uniqueList = new ArrayList<>(set);
        // iterator use
        iteratorUse((ArrayList<String>) list);
        return (ArrayList) list;
    }

    static void sortingArrayList(ArrayList<String> list, ArrayList<FunnyStudent> funnyStudents) {
        Collections.sort(list);
        Collections.sort(list, Collections.reverseOrder()); // reverse
        // sorting list of objects by sth

        System.out.println(funnyStudents);
        Collections.sort(funnyStudents, new SortByRule());
        System.out.println(funnyStudents);
    }

    static void filteringArrays(ArrayList<FunnyStudent> list) {
        Map<Object, List<FunnyStudent>> longerThan2 = list.stream()
                .filter(element -> element.name.length() >= 5)
                .collect(Collectors.groupingBy(funny -> funny.address));

        System.out.println(longerThan2);
    }

    static void iteratorUse(ArrayList<String> lista) {
        Iterator<String> iterator = lista.iterator();
        ArrayList<Integer> elementsSizes = new ArrayList<>();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("123")){
                elementsSizes.add(Integer.parseInt(element));
            }
            else if (iterator.)
            else{
                elementsSizes.add(element.length());
            }
            System.out.println("siemano");
        }
    }

}

class FunnyStudent {
    public int roolNo;
    public String name;
    public String address;

    public FunnyStudent(int roolNo, String name, String address) {
        this.roolNo = roolNo;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "This Student has: " + this.roolNo + " \t" + this.name + "\t" + this.address;
    }
}

// sort by
class SortByRule implements Comparator<FunnyStudent> {
    public int compare(FunnyStudent one, FunnyStudent two) {
        return one.roolNo - two.roolNo;
    }
}
/**
 * LinkedList: LinkedList to struktura danych oparta na liście podwójnie wiązanej. Jest wydajna w operacjach na początku i końcu listy.
 * <p>
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
 * Stack: Stack to kolekcja, która działa na zasadzie stosu (LIFO - Last-In-First-Out).
 * <p>
 * Vector: Vector to dynamiczna tablica, podobna do ArrayList, ale jest w pełni zsynchronizowana.
 * <p>
 * Hashtable: Hashtable to implementacja mapy, podobna do HashMap, ale jest w pełni zsynchronizowana.
 * <p>
 * ConcurrentHashMap: ConcurrentHashMap to implementacja mapy, która jest zoptymalizowana pod kątem współbieżności i może być używana w wielowątkowych środowiskach.
 * <p>
 * LinkedHashMap: LinkedHashMap to implementacja mapy, która zachowuje kolejność dodawania elementów.
 */
