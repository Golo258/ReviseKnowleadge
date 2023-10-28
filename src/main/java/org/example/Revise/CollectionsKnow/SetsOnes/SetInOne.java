package org.example.Revise.CollectionsKnow.SetsOnes;

import java.util.*;

public class SetInOne {

    /**
     Zbior unikalnych elementów
     Nie jest uporzadkowany ani posortowany
     Wykorzystuje mechnizm hashowania do przechowywania i sprawdzania unikalnosci elemnetów
     */
    public void showHashSet(){
        Set<String> names = new HashSet<>();
        names.add("Marian");
        names.add("Staszek");
        names.add("Marian");
        names.add("Lucjusz");
        String searchedName = "Karol";
        if (names.contains(searchedName)){
            System.out.println("There is a " + searchedName + " name in set");
        }
        else{
            System.out.println("There isn't one " + searchedName + " name in set");
            names.add(searchedName);
        }
        System.out.println(names);
    }

    /**
     Zachowuje unikalność elementów
     Utrzymuje kolejnosc w jakiej zostały dodane
     */
    public void showLinkedHashSet(){
        Set<String> colorfulVegatables = new LinkedHashSet<>();
        colorfulVegatables.add("Salad");
        colorfulVegatables.add("Potato");
        colorfulVegatables.add("Cukinia");
        colorfulVegatables.add("Cucumber");
        System.out.println(colorfulVegatables);
        String searchedVege= "Potato";
        if (!colorfulVegatables.contains(searchedVege)){
            colorfulVegatables.add(searchedVege);
        }
        else{
            colorfulVegatables.stream().forEach(elem -> System.out.print(elem + "\t"));
        }
    }

    /**
        Tree Set
     Przechowuje w porzadku naturalnym lub na podstawie komparatora
     automatycznie sortowane -> drzewo Czerwono-Czarne
     */
    public void showTreeSet(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String one, String another) {
                return one.length() - another.length();
            }
        };
        Set<String> flowersTree = new TreeSet<>(comparator);
        flowersTree.add("róża");
        flowersTree.add("fiołek");
        flowersTree.add("tulipan");
        flowersTree.add("róża"); // Duplikat, zostanie zignorowany
        flowersTree.add("lilia");
        System.out.println("\n" + flowersTree);
    }
    public void showEnumSet(){
        enum CardColor{
            RED, GREEN, BLUE, YELLOW, PURPLE
        }
        EnumSet<CardColor> cardColors = EnumSet.of(
                CardColor.GREEN, CardColor.RED, CardColor.BLUE, CardColor.PURPLE
        );
        cardColors.add(CardColor.RED);
        System.out.println(cardColors);
    }
    public void showConcurrentSkipListSEt(){
//        TODO when threads
//        CopyOnWriteArraySet and CopyOnWriteArraySet
    }
    public void showBitSet(){
        BitSet bitSet = new BitSet(5);
//        set - ustawia na 1
        bitSet.set(2);
        boolean isIndexSet = bitSet.get(2);
        System.out.println("Index 2: " + isIndexSet);
//        operacje logiczne  and or xor andNot
        BitSet primeSet = new BitSet(30);
        for (int i = 2; i <= 29; i++) {
            primeSet.set(i);
        }
        for (int i = 2; i * i <= 29; i++) {
            if (primeSet.get(i)) {
                for (int j = i * i; j <= 29; j += i) {
                    primeSet.clear(j);
                }
            }
        }
        System.out.println("Prime numbers from 2 to 29: " + primeSet);
    }

}
