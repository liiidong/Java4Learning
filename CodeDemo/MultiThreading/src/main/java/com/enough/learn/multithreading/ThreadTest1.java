package com.enough.learn.multithreading;

/**
 * @program: CodeDemo
 * @description: 多线程测试类1
 * @author: lidong
 * @create: 2020/05/25
 */
public class ThreadTest1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    // 运行main方法可以看到控制台输出，主线程main和子线程thread1相关信息打印，比如：
    //main  65
    //Thread-0 96
    //Thread-1 28
    //Thread-0 97
    //Thread-0 98
    //main  66
    //Thread-0 99
    //Thread-1 29
    //main  67
    //Thread-1 30
    //main  68
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if(i == 20){
                // 开启两个线程 0 和 1
                new ThreadTest1().start();
                new ThreadTest1().start();
            }
        }
    }
}
