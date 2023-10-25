package org.example.LabyKnowleadge;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final Lock lock = new ReentrantLock();
    private int saldo = 0;

    public synchronized void wpłata(int kwota) {
        System.out.println("Thread: " + Thread.currentThread().getName()
                + "\twplata: " + kwota + "\t saldo: " + this.saldo);
        this.saldo += kwota;
    }

    public synchronized  void wyplata(int kwota) {
        System.out.println("Thread: " + Thread.currentThread().getName()
                + "\twypłata: " + kwota + "\t saldo: " + this.saldo);
        this.saldo -= kwota;
    }

    public int pobierzSlado() {
        return saldo;
    }
}
