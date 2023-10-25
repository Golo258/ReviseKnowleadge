package org.example.LabyKnowleadge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lab3Files {

    public static void main(String[] args) throws IOException {
        BoardGame game = new BoardGame();
//        zapisywanie do pliku
        /*
        Pisanie i czytanie
        List<BoardGame> games = game.loadFromFile("Games.csv");
        write_to_file_binary(games);
        read_from_file();
        */
//            wielowatkek
//        runableTest();

//

    }




    public static void read_from_file() throws FileNotFoundException {
        List<BoardGame> tempList = new ArrayList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("games.bin")
            );

            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                tempList.add((BoardGame) input.readObject());
            }
            System.out.println(tempList.toString());


        } catch (Exception exception) {
            System.err.println(exception);
        }
        System.out.println(tempList.size());
    }

    public static void write_to_file(List<BoardGame> games) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("new_game.txt", StandardCharsets.UTF_8));
        for (BoardGame g : games) {
            writer.write(g.toString());
            writer.newLine();
            writer.write("\n-------------------------\n");
            writer.flush();
        }
    }

    public static void write_to_file_binary(List<BoardGame> games) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(
                new FileOutputStream("games.bin")
        );
        objOut.writeInt(games.size());
        for (BoardGame g : games) {
            objOut.writeObject(g);
            objOut.flush();
        }
        objOut.writeInt(games.size());
        objOut.close();

    }

}
