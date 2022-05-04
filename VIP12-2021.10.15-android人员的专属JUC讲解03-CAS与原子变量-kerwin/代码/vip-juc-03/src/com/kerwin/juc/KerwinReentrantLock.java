package com.kerwin.juc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class KerwinReentrantLock {

    private final AtomicBoolean locked = new AtomicBoolean();
    private Queue<Thread> waits = new ConcurrentLinkedQueue<>();


    public void lock(){
        Thread t = Thread.currentThread();
        waits.add(t);
        //(waits.peek() != t   最上面线程等于当前，则重入，同一个人，不需要CAS检测
        //!locked.compareAndSet(false,true) 否则，做CAS检测
        while(waits.peek() != t || !locked.compareAndSet(false,true)){
            LockSupport.park(this);//wait
        }
        waits.remove();
    }


    public void unlock(){
        locked.set(false);
        LockSupport.unpark(waits.peek());//notifay
    }

}
