package org.example.LabyKnowleadge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGame  implements Serializable{

    private String title;
    private int year;
//    private String brief;
    private double range;
    private String age;
    private short minPlayer;
    private short maxPlayer;
    private double difficulty;
    private String timePlay;
    private double price;
    private String[] artists;
    private String[] developers;
    private String[] categories;

    public BoardGame() {}

    public BoardGame(String title, int year, double range, String age, short minPlayer, short maxPlayer, double difficulty, String timePlay, double price, String[] artists, String[] developers, String[] categories) {
        this.title = title;
        this.year = year;
        this.range = range;
        this.age = age;
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.difficulty = difficulty;
        this.timePlay = timePlay;
        this.price = price;
        this.artists = artists;
        this.developers = developers;
        this.categories = categories;
    }

    public String bestGame(List<BoardGame> boardGames){
        double highestRanking = 0;
        BoardGame bestGame = null;
        for (BoardGame game : boardGames) {
            if (game.title.contains("a")) {
                if (game.range > highestRanking) {
                    highestRanking = game.range;
                    bestGame = game;
                }
            }
        }
//        System.out.println(bestGame.name);
        return "Najlepsza gra: " + bestGame.toString();
    }

    public List<BoardGame> loadFromFile(String path) throws IOException {
        BufferedReader buff = new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8)); // reading whole file
        String line;
        List<BoardGame> tempGame = new ArrayList<>();
        while ((line = buff.readLine())!=null) {
            String[] temp = line.split(";");
            BoardGame g = new BoardGame(
                    temp[0],
                    Integer.parseInt(temp[1]),
                    Double.parseDouble(temp[3]),
                    temp[4],
                    Short.parseShort(temp[5]),
                    Short.parseShort(temp[6]),
                    Double.parseDouble(temp[7]),
                    temp[8],
                    Double.parseDouble(temp[9]),
                    null, null, null
            );
            if(temp[10].equals("N/A")){
                g.setArtists(new String[]{});
            }else{
                g.setArtists(temp[10].split(","));
            }
            if(temp[11].equals("N/A")){
                g.setDevelopers(new String[]{});
            }else{
                g.setDevelopers(temp[11].split(","));
            }
            if(temp[12].equals("N/A")){
                g.setCategories(new String[]{});
            }else{
                g.setCategories(temp[12].split(","));
            }
            tempGame.add(g);
        }
        buff.close(); // we close this fella
        return tempGame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public short getMinPlayer() {
        return minPlayer;
    }

    public void setMinPlayer(short minPlayer) {
        this.minPlayer = minPlayer;
    }

    public short getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(short maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public String getTimePlay() {
        return timePlay;
    }

    public void setTimePlay(String timePlay) {
        this.timePlay = timePlay;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getArtists() {
        return artists;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }

    public String[] getDevelopers() {
        return developers;
    }

    public void setDevelopers(String[] developers) {
        this.developers = developers;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", range=" + range +
                ", age='" + age + '\'' +
                ", minPlayer=" + minPlayer +
                ", maxPlayer=" + maxPlayer +
                ", difficulty=" + difficulty +
                ", timePlay='" + timePlay + '\'' +
                ", price=" + price +
                ", artists=" + Arrays.toString(artists) +
                ", developers=" + Arrays.toString(developers) +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
