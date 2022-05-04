package com.kerwin.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AomicDemo {


    static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 1000000; j++) {

                i.incrementAndGet();

            }
        });

        Thread t2 = new Thread(()->{
            for (int j = 0; j < 1000000; j++) {
                i.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);

    }



}
