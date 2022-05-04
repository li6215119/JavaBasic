package com.kerwin.redfix;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

//对象---类---
//汽车----汽车类
//苹果----苹果类
//类-----类类
//object----?
public class LoopRun {




    static {
        System.out.println();
        //.....


    }


    public static void main(String args[]) throws Exception {



        //显示调用
        Person p = new Person();

        //隐式调用
        Class c = Class.forName("com.kerwin.redfix.Person");
        p = (Person) c.newInstance();

        while (true) {
            try {
                //1. 创建自定义类加载器的实例
                MyClassLoader loader = new MyClassLoader("F:\\工作相关\\redfix\\out\\production\\redfix");
                //2. 加载指定的类
                Class clazz = loader.findClass("com.kerwin.redfix.Demo1");
                //3. 创建运行时类的实例
                Object demo = clazz.newInstance();
                //4. 获取运行时类中指定的方法
                Method m = clazz.getMethod("hot");
                //5. 调用指定的方法
                m.invoke(demo);
                Thread.sleep(5000);



            } catch (Exception e) {
                System.out.println("not find");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

}
