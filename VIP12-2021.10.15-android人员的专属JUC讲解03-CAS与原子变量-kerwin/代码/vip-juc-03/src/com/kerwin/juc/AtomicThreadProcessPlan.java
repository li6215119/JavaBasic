package com.kerwin.juc;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class AtomicThreadProcessPlan {

    public static int counter;

    public static AtomicInteger counter2 = new AtomicInteger();




    public static void main(String[] args) throws InterruptedException {

        //充当一把锁，信息被锁了，没被锁
        //为什么要用一个对象来加锁？ 锁到底加的是什么玩意？
        //线程1进来利用了obj--->应该先记录一下，标记一下
        //立刻
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++){

//                counter++;//原始操作
//                counter2.getAndIncrement();//原子操作
                //原子操作原理
                while(true){
                    int prev = counter2.get();
                    int next = prev + 1;
                    if(counter2.compareAndSet(prev,next)){
                        break;
                    }
                }

            }
        });
        //30分钟后才来
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
//               counter--;
                counter2.getAndDecrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
//        System.out.println(counter);
        System.out.println(counter2.get());
    }




}
