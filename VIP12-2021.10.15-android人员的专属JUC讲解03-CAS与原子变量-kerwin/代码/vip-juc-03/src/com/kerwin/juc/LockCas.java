package com.kerwin.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class LockCas {


    //0 没加锁  1加锁
    private AtomicInteger state = new AtomicInteger(0);


    public void lock(){
        while(true){//核的支撑-->最大限度保证不进行线程上下文的切换
            if(state.compareAndSet(0,1)){
                break;
            }
        }
    }

    public void unlock(){
        state.set(0);
    }


    static int i = 0;
    public void test(){
        synchronized (this){
            i++;
        }
    }


}
