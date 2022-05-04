package com.kerwin.juc;

import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends Thread {
    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    public ReentrantLockTest(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        //难用  API一堆，某个API用在某种场景适用。
        for (int j = 0; j < 100000; j++) {
            //synchronized () 对象头--》monitor
            lock.lock();
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);
    }
}
