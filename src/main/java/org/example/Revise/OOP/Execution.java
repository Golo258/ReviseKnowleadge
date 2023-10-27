package org.example.Revise.OOP;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Execution {

    public static void main(String[] args) {
//        InheritanceResults();
        CollectionsResults();
    }

    static void CollectionsResults(){
        CollectionsRevise cols = new CollectionsRevise();
        cols.showEachCollections();

    }
    static void InheritanceResults() {
        System.out.println("Differences");
        Figure figure = new Figure(12.5);
        SquareFigure square = new SquareFigure(12.5, "kwadrat");
        List<Figure> figures = Arrays.asList(figure, square);
        List<Figure> figuresSecondWay = new ArrayList<>(List.of(figure, square));
        for (var fig : figures) {
            System.out.println(fig.getSquare());
        }
        cleanOutput();
//        #-----------------------------
        /// polimorfizm
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle();
        shapes[1] = new Rectangle();
        shapes[2] = new Shape();
        for (Shape shape : shapes) {
            shape.draw();
        }
//        #------------------
        //abstract
        cleanOutput();
        Animal dog = new Dog("Dragus");
        Animal cat = new Cat("Mruczek");
        dog.makeSound();
        cat.makeSound();
//        #---------------------------------------
        // Interfaces
        cleanOutput();
        BMW bmw = new BMW();
        bmw.start();
        bmw.stop();
        bmw.playMusic();

        // Rekordy
        cleanOutput();
        Library library = new Library();

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 2001);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);

        library.addBook(book1);
        library.addBook(book2);

        List<Book> recentBooks = library.getBooksPublishedAfterYear(2000);

        System.out.println("Recent Books:");
        for (Book book : recentBooks) {
            System.out.println(book);
        }
//        #----------------------------
        cleanOutput();
        // serializacja
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream("student.ser")
            );
            Student student = new Student("Alice", 20, "1234");
            output.writeObject(student);
            System.out.println(student);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // Deserializacja
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) inputStream.readObject();
            System.out.println("Deserialized: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //------------------------------------------------
        // wyjatki
        cleanOutput();
        DbConnection dbConnection = new DbConnection(false);
        try{
            dbConnection.connect();
        } catch (CustomException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }
        catch(Exception exception){
            System.out.println("Different exception " + exception.getMessage());
        }
        finally{
            System.out.println("It's going to execute");
        }

    }

    public static void cleanOutput() {
        System.out.println("\n-----------------------------------\n");
    }
}
