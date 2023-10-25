package org.example.LabyKnowleadge;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab2Streams {
    public static void main(String[] args) {
        BoardGame game = new BoardGame();
        try {
            System.out.println("-----------------------------------\n");
            List<BoardGame> games = game.loadFromFile("Games.csv");
            Supplier<Stream<BoardGame>> uber = games::stream;
            //  range is between 6 and 7.5
            long gamesCount = games.stream()
                    .filter(juego -> juego.getRange() > 6.5 && juego.getRange() <= 7.5)
                    .filter(juego -> juego.getYear() > 2015)
                    .count();
            List<BoardGame> gamesWithRange = games.stream()
                    .filter(juego -> juego.getRange() == 7.5)
                    .toList();
//            System.out.println(gamesWithRange);
//            System.out.println(uber.get().collect(Collectors.toList()));
            Map<Integer, List<BoardGame>> groupedByYear = games.stream()
                    .collect(Collectors.groupingBy(BoardGame::getYear));

            for (Map.Entry<Integer, List<BoardGame>> maps : groupedByYear.entrySet()) {
                // sout  System.out.println(maps.getKey() + "\t: " + maps.getValue());
                int x = 12;
            }
            var byArtist = uber.get().collect(Collectors.groupingBy(BoardGame::getArtists));
            BoardGame firstGame = games.stream()
                    .filter(juego -> juego.getMinPlayer() == 3).findFirst().orElse(null);
            if (firstGame != null) System.out.println(firstGame.toString());
            else System.out.println("Is null");
            boolean matchGame = games.stream()
                    .anyMatch(juego -> juego.getRange() == 6.5); // element exists
            System.out.println("----------------\n");
            task5(games);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public static void task5(List<BoardGame> games) {
        // point f avarege price of games published between 2005 and 2012
        OptionalDouble average_of_pirces = (games.stream()
                        .filter(juego -> juego.getYear() >= 2005 && juego.getYear() <= 2012)
                        .toList())
                        .stream().mapToDouble(BoardGame::getPrice).average();

        System.out.println("Average of prices:" + average_of_pirces);
        // i. Wyszukaj wszystkie gry dla, których deweloperów (developers) jest więcej niż dwie osoby.


    }
}
