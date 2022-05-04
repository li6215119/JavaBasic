package com.kerwin.juc;

/**
 * JMM内存模型示例
 * @author Kerwin
 * @time 2021-10-11
 */
public class JMMTest {


    //volatile  ???  加了这个我就能够同步了？

    //具备有我们的可见性
    //volatile到底做了什么？
    public static volatile boolean initFlag = false;


    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("begin...........");
            while(!initFlag){
                try {
                    //sleep和wait的区别，wait会释放资源
                    //两者都会重新加载高速缓存内容，会释放高速缓存内容
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ending.............");
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            prepare();
        }).start();
    }
    public static void prepare(){
        System.out.println("prepare assgin  begin.......");
        initFlag = true;
        System.out.println("prepare assgin  end.......");
    }
}
