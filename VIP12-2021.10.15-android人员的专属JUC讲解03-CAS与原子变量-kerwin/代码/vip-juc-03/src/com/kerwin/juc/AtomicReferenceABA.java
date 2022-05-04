package com.kerwin.juc;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 */
public class AtomicReferenceABA {

    static AtomicReference<String> ref = new AtomicReference<>("A");

    //版本记录
    static AtomicStampedReference<String> ref2 = new AtomicStampedReference<>("A",0);

    //变动结果记录
    static AtomicMarkableReference<String> ref3 = new AtomicMarkableReference<>("A",false);


    public static void main(String[] args) throws InterruptedException {


        System.out.println("main start....");
        String prev = ref.get();
        other();
        Thread.sleep(1000);
        //最终执行，原本里面是A，所以当前为true
        //实际业务角度出发，没有
        //实际的数据操作角度来讲，动了
        //有业务需要对于整个数据是否变动进行判定
        //AtomicStampedReference   提供版本记录，能够追溯轨迹
        //AtomicMarkableReference  我不需要知道估计我只要知道结果，有没有该
        System.out.println("change  A->C"+ref.compareAndSet(prev,"C"));
    }

    public static void other(){

        //更改为B
        new Thread(()-> {
            System.out.println("change A->B"+ref.compareAndSet(ref.get(),"B"));
        }).start();

        //改回去A
        new Thread(()-> {
            System.out.println("change B->A"+ref.compareAndSet(ref.get(),"A"));
        }).start();
    }



}
