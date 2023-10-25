package org.example.LabyKnowleadge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Lab4Threads {

    public static void main(String[] args) {
        Runnable runBank = () -> {
            Bank bank = new Bank();
            Random random = new Random();
            if (random.nextBoolean()){
                bank.wpłata((int) Math.pow(5, 2));
            }
            else{
                bank.wyplata((int) Math.sqrt(40.0));
            }
        };
//        fixedThread(runBank);
//        catchedThread(runBank);
//        callableThread();
        lambdaCallable();
    }
    public static void runableTest() {
        Runnable runTest = new Runnable() {
            @Override
            public void run() {
                Bank bank = new Bank();
                Random rand = new Random();
                for (int i = 0; i < 50; i++) {
                    if (rand.nextBoolean()) {
                        bank.wpłata(i * 2);
                    } else {
                        bank.wyplata(i * 2);
                    }
                    System.out.println(i);
                    try {
                        Thread.sleep(500); // 0.5s
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Runnable runTest2 = () -> {
            Bank bank = new Bank();
            Random rand = new Random();
            for (int i = 0; i < 50; i++) {
                if (rand.nextBoolean()) {
                    bank.wpłata(i * 2);
                } else {
                    bank.wyplata(i * 2);
                }
                try {
                    Thread.sleep(500); // 0.5s
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread th1 = new Thread(runTest);
        Thread th2 = new Thread(runTest2);
        th1.start();
        th2.start();
    }

    static void fixedThread(Runnable runBank){
        ExecutorService serviceFixed = Executors.newFixedThreadPool(4); // konkretna liczba watkow

        for (int i =0 ; i < 1000; i++){
            serviceFixed.submit(runBank);
        }
        serviceFixed.shutdown();
        System.out.println("End of main thread");
    }
    static void catchedThread(Runnable runBank){
        ExecutorService serviceCatched = Executors.newCachedThreadPool(); // konkretna liczba watkow
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) serviceCatched;
        int taskNumber = 0, threadNumber = 5;
        while(taskNumber++ < 1000){
            if (threadPoolExecutor.getActiveCount() < 5){
                threadPoolExecutor.submit(runBank);
            }
        }
    }
    static void callableThread(){
        ExecutorService serviceCatched = Executors.newCachedThreadPool(); // konkretna liczba watkow
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) serviceCatched;
        List<Future<Integer>> arrayFutureList = new ArrayList<>();


        int taskNumber = 0, threadNumber = 5;
        while(taskNumber < 1000){
            if (threadPoolExecutor.getActiveCount() < 5){
                StartCallable callable = new StartCallable();
                arrayFutureList.add(threadPoolExecutor.submit(callable));
                taskNumber++;
            }
        }
        for( int i = 0; i< 1000; i++){
            Future<Integer> future = arrayFutureList.get(i);
            try{
                int result = future.get(1, TimeUnit.SECONDS);
                System.out.println("Wynik koncowy losowania: " + result);
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }
    static void lambdaCallable(){
        Callable<Integer> runCall = () -> {
            Random rand = new Random();
            int numb = rand.nextInt(2000);
            System.out.println("Watek: " + Thread.currentThread().getName() +
                    "\twylosowal " + numb);
            Thread.sleep(500);
            return numb;
        };

        List<FutureTask<Integer>>  futureTask= new ArrayList<>();

//        thread1.start();
//        try{
//            futureTask.get(1, TimeUnit.SECONDS);
//
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        catch (TimeoutException e){
//            throw new RuntimeException(e);
//        }
    }
}
