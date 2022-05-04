package com.kerwin.juc;

public class Student {

    volatile String name;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
