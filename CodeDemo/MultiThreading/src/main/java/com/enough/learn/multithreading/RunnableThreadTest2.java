package com.enough.learn.multithreading;

/**
 * @program: CodeDemo
 * @description:
 * @author: lidong
 * @create: 2020/05/25
 */
public class RunnableThreadTest2 implements Runnable{
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    // 运行main方法，看到控制台输出效果和ThreadTest1类似
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if(i == 5){
                RunnableThreadTest2 runnableThreadTest2 = new RunnableThreadTest2();
                new Thread(runnableThreadTest2,"线程1").start();
                new Thread(runnableThreadTest2,"线程2").start();
            }
        }
    }
}
