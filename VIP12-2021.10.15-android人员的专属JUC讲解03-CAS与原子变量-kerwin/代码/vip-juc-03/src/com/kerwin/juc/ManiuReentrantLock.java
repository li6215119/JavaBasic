package com.kerwin.juc;

public class ManiuReentrantLock {



    public static int counter = 0;
    static KerwinReentrantLock reentrantLock = new KerwinReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                counter++;
                reentrantLock.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                counter--;
                reentrantLock.unlock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(counter);
    }

}
