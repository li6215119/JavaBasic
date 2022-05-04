package com.kerwin.juc;

public class SingerDemo {

    //规避，
    private static volatile SingerDemo singerDemo;

    private SingerDemo(){



    }

    /**
     *  monitorenter
     * 12 getstatic #2 <com/kerwin/juc/SingerDemo.singerDemo : Lcom/kerwin/juc/SingerDemo;>
     * 15 ifnonnull 28 (+13)
     * 18 new #3 <com/kerwin/juc/SingerDemo>
     * 21 dup
     * 22
     * 25 putstatic #2 <com/kerwin/juc/SingerDemo.singerDemo : Lcom/kerwin/juc/SingerDemo;>
     *     invokespecial #4 <com/kerwin/juc/SingerDemo.<init> : ()V> 堆内部对象进项相关分配
     * 28 aload_0
     * 29 monitorexit
     * @return
     */
    public static SingerDemo getInstance(){
        if(singerDemo == null){
            synchronized (singerDemo){
                if(singerDemo == null){
                    singerDemo = new SingerDemo();
                }
            }
        }
        return singerDemo;
    }




}
