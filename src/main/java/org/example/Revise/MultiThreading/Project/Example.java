package org.example.Revise.MultiThreading.Project;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
    public static void main(String[] args) {
        Thread th1 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++)
                        System.out.println(Thread.currentThread().getName() + " at " + i + " is  running ");
                }
        );
        Thread th2 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++)
                        System.out.println(Thread.currentThread().getName() + " at " + i + " is  running ");
                }
        );
//        runTwoThreads(th1, th2);
        pulaWatkow();
    }
    static void runTwoThreads(Thread th1, Thread th2){
        // Priorytet 1-10 - gdzie 10 to najwyższy priorytet
        th1.setPriority(1); //
        th2.setPriority(10);

        th1.setName("T1");
        th2.setName("T2");
//        th1.run(); // uruchomienie petli w głownym watku aplikacji

        th1.start(); // uruchomienie petli w osobnym watku
        th2.start();
    }

    static void pulaWatkow() {
        ExecutorService onlyOneThread = Executors.newSingleThreadExecutor();
//        Narzuona ilosc watkow do dyspozycji
        int numberOfPossibleThreads = 2;
        ExecutorService fixedService = Executors.newFixedThreadPool(numberOfPossibleThreads);
//         pool of threads - pula watków
        // & Podajemy tylko co ma robić, a pula wątków się tym zajmuje
        fixedService.submit(
                () -> numbers()
        );
        fixedService.submit(
                () -> numbers()
        );
        fixedService.shutdown(); // zamyka pule  po zakonczeniu watkow
//        fixedService.shutdownNow() // zamyka pule w trakcie jego trwania, nie ważne czy sie skonczył


    }

    public static void numbers(){
        for (int i = 0; i < 50; i++)
            System.out.println(Thread.currentThread().getName() + " at " + i + " is  running ");
    }
}
