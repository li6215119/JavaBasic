package com.kerwin.juc;

import java.util.HashSet;

/**
 * 指令重排序
 */
public class CodeReset {

    public static int x,y,a,b;


    public static void main(String[] args) {
        try {
            testCodeReset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void testCodeReset() throws InterruptedException {
        HashSet set = new HashSet();
        for (int i = 0; i< 10000000;i++){
            a = 0;
            b = 0;
            y = 0;
            x = 0;

            Thread thread1 = new Thread(() -> {
                a = y;
                x = 1;
            });

            //a = 0, b= 0
            //a = 1, b= 0
            //a = 0, b= 1
            //a = 1, b= 1
            //
            Thread thread2 = new Thread(() -> {
                b = x;
                y = 1;
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            set.add("a = "+ a + " , "+" b = "+b);
            System.out.println(set);

        }
    }

}
