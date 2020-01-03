package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @program: rocketmq-demo
 * @description:
 * @author: lidong
 * @create: 2020/01/02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer demo01Producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult sendResult = demo01Producer.syncSend(id);
        log.info("[testSyncSend][发送编号:[{}] 发送结果:[{}]", id, sendResult);
        // 阻塞等待，保证消费
        //        new CountDownLatch(1).wait();
        //await()才是CountDownLatch里相应的等待函数。
        //wait()是同步锁，是Object类的方法，与notify()配对使用的，使用时必须要有sychronized关键字。
        new CountDownLatch(1).await();

    }

    @Test
    public void testAsyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        demo01Producer.asyncSend(id, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[testSyncSend][发送编号:[{}] 发送结果:[{}]", id, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("[testSyncSend][发送编号:[{}] 发送异常:[{}]", id, throwable);
            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testOnewaySend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        demo01Producer.onewaySend(id);
        log.info("[testSyncSend][发送完成:[{}]]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
