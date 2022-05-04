package com.kerwin.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicFiledTest {



    static Student s = new Student();
    public static void main(String[] args) {

        //去保证某个对象内的字段的原子操作
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        updater.compareAndSet(s,null,"kerwin");



        //内存 10    10  11
        System.out.println(s);
    }



}
