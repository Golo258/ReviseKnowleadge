package org.example.Revise.CollectionsKnow.MapsOnes;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
}
