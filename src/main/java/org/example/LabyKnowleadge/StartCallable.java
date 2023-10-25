package org.example.LabyKnowleadge;

import java.util.Random;
import java.util.concurrent.Callable;

public class StartCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        int numb = rand.nextInt(2000);
        System.out.println("Watek: " + Thread.currentThread().getName() +
                "\twylosowal " + numb);
        Thread.sleep(500);
        return numb;
    }
}
