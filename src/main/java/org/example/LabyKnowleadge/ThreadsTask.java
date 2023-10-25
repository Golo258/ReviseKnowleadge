package org.example.LabyKnowleadge;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadsTask {
    public static void main(String[] args) {
        task4();
    }

    static void task3() {
        //Wykorzystując dowolny rodzaj wątku wyszukaj liczby pierwsze dla zakresu 1 000 000.
        // Do zadania wykorzystaj 4 wątki.

    }

    static void task4() {
        //4. Napisz program, który wykorzystując wielowątkowość będzie losował 1000 liczb
        //spełniających warunek podzielności przez 3. Wszystkie liczby spełniające ten warunek
        //dodawaj do wspólnej listy, do rozwiązania wykorzystaj dowolny rodzaj wątków.

        Callable<Integer> callable = () -> {
            Random random = new Random();
            int randNr = random.nextInt(2000);
            while (randNr % 3 != 0) {
                randNr = random.nextInt(2000);
            }
            Thread.sleep(500);
            return randNr;
        };
        FutureTask<Integer> tasks = new FutureTask<>(callable);
        Thread th1 = new Thread(tasks);
        th1.start();
        try{
            System.out.println(tasks.get(1, TimeUnit.SECONDS));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    static int isPrime(int i) {
        int j;
        if (i == 1 || i == 2)
            return 1;
        if (i % 2 == 0)
            return 0;

        for (j = 3; j < Math.sqrt(i); j += 2)
            if (i % j == 0)
                return 0;

        return 1;
    }
}
