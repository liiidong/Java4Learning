package com.enough.learn.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: CodeDemo
 * @description:
 * @author: lidong
 * @create: 2020/05/25
 */
public class CallableThreadTest3 implements Callable {
    public Object call() throws Exception {
        int i = 0;
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    //运行main方法，控制台输出类似
    //main  31
    //main  32
    //返回值的线程3 0
    //返回值的线程3 1
    //返回值的线程3 2
    //返回值的线程3 3
    //返回值的线程3 4
    //返回值的线程3 5
    //返回值的线程3 6
    //返回值的线程3 7
    //返回值的线程3 8
    //返回值的线程3 9
    //main  33
    //返回值的线程3 10

    //子线程callableThreadTest3所在任务调度对象futureTask执行完毕后输出：-> 子线程的返回值是：100
    public static void main(String[] args) {
        CallableThreadTest3 callableThreadTest3 = new CallableThreadTest3();
        //构造一个可取消的异步计算任务对象，用来获取线程执行完毕的返回值
        FutureTask <Integer> futureTask = new FutureTask <Integer>(callableThreadTest3);
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                new Thread(futureTask, "返回值的线程1").start();
                new Thread(futureTask, "返回值的线程2").start();
            }
            //尝试终端异步计算任务
            if (i == 30) {
                System.out.println("取消异步计算任务");
                futureTask.cancel(false);
            }
            //实时输出异步计算是否完成
            System.out.println("异步计算任务是否完成：" + futureTask.isDone());
        }
        try {
            //拿到【异步计算的结果】返回值后可以做相关的业务处理逻辑
            System.out.println("子线程的返回值是：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
