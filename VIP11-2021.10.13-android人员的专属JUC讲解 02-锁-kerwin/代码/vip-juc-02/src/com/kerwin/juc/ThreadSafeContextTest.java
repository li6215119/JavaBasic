package com.kerwin.juc;

public class ThreadSafeContextTest {

    public volatile static int counter;

    public static void main(String[] args) throws InterruptedException {

        //充当一把锁，信息被锁了，没被锁
        //为什么要用一个对象来加锁？ 锁到底加的是什么玩意？
        //线程1进来利用了obj--->应该先记录一下，标记一下
        Object obj = new Object();//无锁定状态
        byte[] b = new byte[0];//96位数据=头数据
        //立刻
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++)
            //临界区
            {
                //可能被多个线程同时访问对他进行读写
                //对象--》提供锁
                //JVM底层实现过程中，对于锁的判断依据全部都扔到了对象上面进行判定
                synchronized (b){//第一个线程启动后碰到synchronize，b对象作为偏向锁
                    counter++;
                }
            }
        });
        //30分钟后才来
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                synchronized (obj){//第二个线程启动后碰到synchronize，b对象作为轻量级
                    counter--;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(counter);
    }




    public synchronized void increment(){
        synchronized (this){

        }
        counter++;
    }

    public static synchronized void decrement(){
        counter--;
    }
    //↓↓↓↓↓↓↓↓↓↓↓等价转换↓↓↓↓↓↓↓↓↓↓↓↓
    public static void decrement2(){
        //堆中：class对象
        //1.内存泄漏的风险
        //A B C 同步管理     D  E F 搞另外一个
        //ThreadSafeContextTest.class   =   类加载---》对立面开一个class实例
       synchronized (ThreadSafeContextTest.class){
           counter--;
       }
    }


}
