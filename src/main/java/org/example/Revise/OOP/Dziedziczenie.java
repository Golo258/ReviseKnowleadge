package org.example.Revise.OOP;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Dziedziczenie
Klasy nadrzędne i pochodne
pochodna dziediczy po klasie nadrzednej
key word - extends - klasa dziedziczy od innej

 */
public class Dziedziczenie {

}

class Figure {
    private double square;

    public Figure(double square) {
        this.square = square;
    }

    double countSquare(int numberOfSides) {
        return this.square * numberOfSides;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }
}

class SquareFigure extends Figure {
    private String nameOfFigure;

    public SquareFigure(double side, String name) {
        super(side);
        this.nameOfFigure = name;
    }

    public String getNameOfFigure() {
        return nameOfFigure;
    }

    public void setNameOfFigure(String nameOfFigure) {
        this.nameOfFigure = nameOfFigure;
    }
}
// -------------------------------------------------------
/*
Polimorfizm
zdolnośc obiektu do przyjmowania wielu form
   implementacja teg osamego interfejsu
   przesłanianie metod
   wielokrotne dziedziczenie interfaców
 */

class Shape { // base class
    public void draw() {
        System.out.println("I draw a figure");
    }
}

class Circle extends Shape { // pochodna klasy

    @Override
    public void draw() {
        System.out.println("I draw a cricle");
    }
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("i draw an rectangle");
    }
}

//----------------------------------------------------
/*
Abstract classes/ methods
definiowanie struktur klas i interfasów, które nie maja pełnej implemetacji
stanowia szkic
key word abstract
abstrakcyjne metody musza byc zaimplementowane w klasach pochodnych dziedzicaczych po abstract
 */
abstract class Animal {
    private String name;

    // abstract method
    public abstract void makeSound();

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Class:" + getClass().getName() + " with attribute: " + getName() + " says Woof!");
    }

}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Meow!");
    }
}
/*
Interasy - mozna dziedziczyć wiele
*/

interface Vehicle {
    final int constant_var = 12;

    void start();

    void stop();
}

interface Radio {
    void playMusic();
}

class BMW implements Vehicle, Radio {
    @Override
    public void start() {
        System.out.println("BMW is starting");
    }

    @Override
    public void stop() {
        System.out.println("BMW stops driving");
    }

    @Override
    public void playMusic() {
        System.out.println("Music is playing" + constant_var + " times");
    }
}

/*
Rekordy: do przechowywania danych , automatycznie generuja
metody equals hascode to string ettery
Pomagaja w tworzeniu klas reprezentujacych dane które nie zawieraja dodatkowje logiki
pola rekordu w {} pola sa niemutowalne - niezmienialne po zainicjowaniu
nie moga byc rozszerzalne przez dziedziczenie
*/
record Book(String title, String author, int publicationYear) {
    public boolean isBestSeller() {
        return publicationYear >= 2000;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooksPublishedAfterYear(int year) {
        return books.stream()
                .filter(b -> b.publicationYear() > year)
                .collect(Collectors.toList());
    }

}
/*
Generics
 */

class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getCntent() {
        return content;
    }
}

class StringBox extends Box<String> {
    public StringBox(String strContent) {
        super(strContent);
    }
}

//#--- inny przykłąd
class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}

class GenericList<T> {
    private ListNode<T> head;

    public void add(T newData) {
        ListNode<T> newNode = new ListNode<>(newData);
        if (head == null) {
            head = newNode;
        } else {
            ListNode<T> current = head;
            while (current.getNext() != null) {

            }
        }
    }
}

/*
Serializacja
-- zapisywanie obiektó do strumienia bajtów
umożliwia przesyłanie obiektó przez siec i zapisywanie do plików
Za pomoca interfasu Serializable

 */
class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}

class Student extends Person {
    private String id;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.id = studentId;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + this.id + "]";
    }
}
//#-----------------------
/*
WYJATKI:
throw new Exception -> wywoływanie wyjątku
throws -> przy funckji okresla że moze zgłaszać wyjatki
ale i ch nie obsługuje

przekazuje odpowiedzialnosc za złąpanie wyjatku przy jej wywołąniu

 */
class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}

class DbConnection {
    private boolean connectionSuccess;

    public DbConnection(boolean connectionSuccess) {
        this.connectionSuccess = connectionSuccess;
    }

    public void connect() throws CustomException {
        if (!isConnectionSuccess()) {
            throw new CustomException("Error with connection");
        } else {
            System.out.println("Connection sucessfull");
        }
    }

    public boolean isConnectionSuccess() {
        return connectionSuccess;
    }

    public void setConnectionSuccess(boolean connectionSuccess) {
        this.connectionSuccess = connectionSuccess;
    }
}










