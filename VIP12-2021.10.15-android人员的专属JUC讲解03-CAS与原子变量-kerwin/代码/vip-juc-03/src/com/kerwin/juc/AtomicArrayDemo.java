package com.kerwin.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AtomicArrayDemo {




    public static void main(String[] args) {
//        testArray();
        testArrayAtomic();
    }
    static int[] array = new int[10] ;
    public static void testArray(){
        List<Thread> ts = new ArrayList<>();

        int length = array.length;
        for (int i = 0; i < length; i++) {

            ts.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    array[j % length]++ ;
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

        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }


    }



    static AtomicIntegerArray atomicArray = new AtomicIntegerArray(10) ;
    public static void testArrayAtomic(){
        List<Thread> ts = new ArrayList<>();

        int length = atomicArray.length();
        for (int i = 0; i < length; i++) {

            ts.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    atomicArray.getAndIncrement(j % length);
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

        System.out.println(atomicArray);


    }



}
