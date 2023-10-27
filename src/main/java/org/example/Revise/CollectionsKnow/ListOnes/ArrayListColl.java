package org.example.Revise.CollectionsKnow.ListOnes;


import java.util.*;
import java.util.stream.Collectors;

public class ArrayListColl {

    /**
     * ArrayList: ArrayList to dynamiczna tablica,
     * która pozwala na przechowywanie i zarządzanie danymi w formie listy.
     * Jest to jedna z najczęściej używanych kolekcji w Javie.
     */

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
            else{
                elementsSizes.add(element.length());
            }
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