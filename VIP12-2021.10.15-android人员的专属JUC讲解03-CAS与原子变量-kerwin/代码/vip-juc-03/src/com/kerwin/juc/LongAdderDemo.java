package com.kerwin.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) {
        testAtomicLong();
        testLongAdder();
    }

    public static void testAtomicLong(){
        AtomicLong atomicLong = new AtomicLong(0);
        List<Thread> ts = new ArrayList<>();

        long begin = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }

        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println();

        System.out.println(atomicLong.get()+"  timer="+(end - begin));
    }


    public static void testLongAdder(){
        //专门用来做数据累加，递减
        LongAdder atomicLong = new LongAdder();
        List<Thread> ts = new ArrayList<>();

        long begin = System.nanoTime();
        for (int i = 0; i < 100; i++) {

            ts.add(new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.increment();
                }
            }));
        }

        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println();

        System.out.println(atomicLong.longValue()+"  timer="+(end - begin));
    }

}
