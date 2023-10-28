package org.example.Revise.CollectionsKnow.MapsOnes;

import com.sun.source.tree.Tree;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OneInPeace {

    /**
     * HashMap:
     * para klucz - wartosc
     * szybki  dostęp do danych na podstawie klucza
     * klucze unikalne - wartosc jakie chcemy
     * kolejnosc przechowywania elementów jest nie jednoznaczna
     */
    public void showHashMap() {
        Map<Integer, String> enumString = new HashMap<>();
        enumString.put(12, "Siemano");
        enumString.put(15, "Kolano");

        String text = enumString.get(15);
        System.out.println("Text content: " + text);
        System.out.println("Text contains text: " + enumString.containsKey(11));
        for (Map.Entry middle : enumString.entrySet()) {
            System.out.println(middle.getKey() + " | " + middle.getValue());
        }
    }

    /**
     * TreeMap -> drzewo binarne
     * elementy są posortowane
     */
    public void showTreeMap() {
        TreeMap<String, Integer> integerTreeMap = new TreeMap<>();
        integerTreeMap.put("John", 52);
        integerTreeMap.put("Anna", 12);
        integerTreeMap.put("Kochman", 105);
        integerTreeMap.put("Marysia", 2);
        System.out.println(integerTreeMap);
        for (Map.Entry<String, Integer> wpis : integerTreeMap.entrySet()) {
            System.out.print(wpis.getKey() + ": " + wpis.getValue() + "\t");
        }
//        Default rule of comparing is alfphabetic with String
        //Adding not default way of comparing
        // na podstawie innej mapy
        TreeMap<String, Integer> ruledTree = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String one, String another) {
                return integerTreeMap.get(one).compareTo(integerTreeMap.get(another));
            }
        });
        ruledTree.putAll(integerTreeMap);
        System.out.println(ruledTree);
        for (Map.Entry<String, Integer> wpis : ruledTree.entrySet()) {
            System.out.print(wpis.getKey() + ": " + wpis.getValue() + "\t");
        }
        System.out.println("\n-----------------------------------\n");
        /// made comparator
        Comparator<String> customComp = new Comparator<String>() {
            @Override
            public int compare(String one, String another) {
                int value1 = integerTreeMap.get(one);
                int value2 = integerTreeMap.get(another);
                if (value1 < value2)
                    return -1;
                else if (value1 > value2)
                    return 1;
                else
                    return 0;
            }
        };
        TreeMap<String, Integer> customTree = new TreeMap<>(customComp);
        customTree.putAll(integerTreeMap);
        try {
            if (!customTree.containsKey("Grażka")) {
                customTree.put("Game", 83);
            } else {
                System.out.println("Grażka is in the tree with value" + customTree.get("Grażka"));
            }
            System.out.println(customTree);
        } catch (Exception exception) {
            exception.getStackTrace();
        }
        System.out.println(customTree);
    }

    /**
     * LinkedHashMap -> zachowuje kolejnosc wstawiania elementów
     */
    public void showLinkedHashMap() {
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("One", 1);
        linked.put("Three", 3);
        linked.put("Two", 2);
        System.out.println(linked);
//        Implemenetacja prostego LRU cashe - ogranicza ilosc elementów, usuwa najstarsze
        int casheSize = 3;
//        Jesli przekroczy rozmiar pamieci to usuwa ten element
        LinkedHashMap<String, Integer> LRUCashe = new LinkedHashMap<String, Integer>(
                casheSize, 0.75f, true
        ) {
//             przysłąniamy metode z LinkedHashMap classy
            protected boolean removeEldestEntry(Map.Entry<String, Integer> elder) {
                return size() > casheSize;
            }
        };
        LRUCashe.put("A", 1);
        LRUCashe.put("B", 2);
        LRUCashe.put("C", 3);
        System.out.println("A from LRU:" + LRUCashe.get("A")); // Odczyt elementu "A", który jest teraz na drugim miejscu
        System.out.println(LRUCashe);
        LRUCashe.put("D", 4);
        System.out.println(LRUCashe);
//         removing elder element
        Predicate<Map.Entry<String, Integer>> condition = entry -> entry.getValue() % 2 ==0;
        // Predicate - interface funkcyjny pozwalający definiować warunki dla danych elementów kolekcji
//        Tutaj sprawdza czy wartosc elementu jest parzysta
        LRUCashe.entrySet().removeIf(condition);
        System.out.println(LRUCashe);
    }
    public void predicateExamples(){
        //Filtering elements
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 12);
        Predicate<Integer> isDevidedBy3 = numb -> numb % 3 == 0;
        System.out.println("All: " + numbers);
        ArrayList<Integer> notDevided = (ArrayList<Integer>) numbers.
                stream().filter(isDevidedBy3).
                collect(Collectors.toList());
        System.out.println("Devided by 3: " + notDevided);
        //----------------------------------------
        //Elemenet belong to set
        Set<String> fruits = new HashSet<>(
                Arrays.asList("coconat","apple", "banana","cherry", "mango")
        );
        Predicate<String> startWithC = fruit -> fruit.startsWith("c");
        boolean anyStartWithC = fruits.stream()
                .anyMatch(startWithC);
        System.out.println("Is Any start with c: " + anyStartWithC);
        // Filtrowanie plików w katalogu
        File dir = new File("org/example/LabyKnowleadge");
        Predicate<File> isJavaFile = file -> file.exists() && file.getName().endsWith(".java");
        File [] javaFiles =  dir.listFiles(file -> isJavaFile.test(file));
        System.out.println(javaFiles);

        //Walidacja danych
        Predicate<String> isEmailValid = email -> email.matches(
                "^[A-Za-z0-9]+@(.+)$"
        );
        String testedEmail = "golon338@gmail.com";
        boolean isValid = isEmailValid.test(testedEmail);
        System.out.println("Is " + testedEmail + " valid: " + isValid);

        // filtrowanie strumieni
        List<String> words = Arrays.
                asList("apple", "banana", "cherry", "date", "fig");
        Predicate<String> isLongFruit = fruit -> fruit.length() > 5;
        List<String> shortWords = words.stream()
                .filter(isLongFruit)
                .collect(Collectors.toList());
        System.out.println(shortWords);

        // Obsługa zdarzeń
        Predicate<Integer> isNumberPositive = numb -> numb > 0;
        int givenNumber = 5;
        if (isNumberPositive.test(givenNumber)){
            System.out.println("Number " + givenNumber + " is positive");
        }
        else{
            System.out.println("Number " + givenNumber + " is negative");
        }
    }
    public void showConcurrentHashMap(){
        // TODO check after the Thread has been learned
    }
    public void showEnumMap(){
        enum WorkingDays {
            MONDAY, TUESDAY, WEDNESDAY,THURSDAY, FRIDAY
        }
        EnumMap<WorkingDays, String> workSchedule = new EnumMap<WorkingDays, String>(WorkingDays.class);
        workSchedule.put(WorkingDays.WEDNESDAY, "refactoring");
        workSchedule.put(WorkingDays.FRIDAY, "chilling");
        workSchedule.put(WorkingDays.MONDAY, "working late");
        for (WorkingDays day: WorkingDays.values()) {
            String activity = workSchedule.get(day);
            if( activity != null){
                System.out.println("Day: " + day + " :" + activity );
            }
        }
    }
}
