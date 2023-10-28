package org.example.Revise.MultiThreading;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.*;

public class ThreadsExplanation {

    public void definingThreads() {
        // inheritance
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("Watek głowny");

        /**
         Unika ograniczenia dziedziczenia
         Umozliwia elastyczne zarzadzanie kodem
         */
        Thread thread1 = new Thread(new MyRunnable(), "funny thread");
        thread1.start();
        System.out.println("Głowny 2");
        /**
         By callable klass
         moze zwracac wynik i zgłaszać wyjątki
         Future ->
         asynchroniczne oczekwianie na zakonczenie zadania
         pobranie wyniku
         */
        Callable<Integer> task = () -> {
            return 42;
        };
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executorService.submit(task);
        try {
            Thread.sleep(500);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        Stopping threads
        StoppingThread stoppingThread = new StoppingThread();
        Thread th1 = new Thread(stoppingThread, "The thread 1 ");
        th1.start();
        try {

            Thread.sleep(1000);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        stoppingThread.requestStop();
        System.out.println("stop request");
    }

    public void showingCallableUse() {
//        Tworzenie puli watków
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // Tworzenie tablicy z zadaniami
        ArrayList<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(new Worker(51));
        tasks.add(new Worker(102));
        tasks.add(new Worker(72));
        tasks.add(new Worker(84));
        // Uruchamianie zadań
        LinkedList<Future<Integer>> futures = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            futures.add(executorService.submit(tasks.get(i)));
        }
        // Oczekiwanie na zakonczenie i pobieranie wyników
        for (int i = 0; i < futures.size(); i++) {
            try {
                Integer result = futures.get(i).get();
                System.out.println("Result of task= " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }


    public void showAsynchronise() {
        Counter counter = new Counter();
        int threadsNr = 5;
        Thread[] watki = new Thread[threadsNr];
        for (int thIndex = 0; thIndex < threadsNr; thIndex++) {
            watki[thIndex] = new Thread(() -> {
                for (int iter = 0; iter < 100; iter++) {
                    counter.increment();
                }
            });
            watki[thIndex].start();
        }
        // waiting until threads teminate jobs
        for (Thread th: watki) {
            try{
                th.join();
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("Total of counter: "+ counter.getCount());
    }

    //
    public void showVollatile() {
        ThreadWithVolatile example = new ThreadWithVolatile();
        Thread thread = new Thread(
                () -> {
                    example.start();
                }
        );
        thread.start();
        try {
            Thread.sleep(1000); // Poczekaj 1 sekundę
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        example.stopThread(); // Zatrzymaj wątek
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

//By Inheritance
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
}

// by implement runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread: " + threadName + " is running ");
    }
}

class StoppingThread implements Runnable {
    private boolean stopRequest = false;

    public synchronized void requestStop() {
        this.stopRequest = true;
    }

    public boolean isStopRequest() {
        return stopRequest;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException E) {
            E.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Class is running");
        while (!isStopRequest()) {
            sleep(1000);
            System.out.println(" ... ");
        }
        System.out.println("Class stopped");
    }
}

class Worker implements Callable<Integer> {
    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Task with " + id + " is running");
        TimeUnit.SECONDS.sleep(2); // time simulation
        System.out.println("Task " + id + " stoppes");
        return id * 10;
    }
}

class Counter {
    private int count = 0;

    /**
     * block syncronized
     * zapewnia że tylko jeden watek moze wejsc do okreslonej sekcji kodu
     * chroni wspóldzielone zasoby przed rownoczesnym dostepem do wielu watkow
     */
    public synchronized void increment() {
        System.out.println("Thread: " + Thread.currentThread().getName() + " increment");
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

class ThreadWithVolatile extends Thread {
    /**
     * volatile : oznacza zmienna jako widoczna dla wielu watkow
     * wiele watkow ma dostep do jej odczytu i zapisu
     * po zmianie od razu inene wątki wiedzą o niej
     * przydatne tylko jako flagi kontrolne
     */
    private volatile boolean isRunning = true;

    public void start() {
        while (isRunning) {
            System.out.println("Th: " + Thread.currentThread().getName() + " is running");
        }
        System.out.println("Has stopped");
    }

    public void stopThread() {
        isRunning = false;
    }
}