package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo06Producer;
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
 * @description: 顺序消费单元测试
 * @author: lidong
 * @create: 2020/01/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo06ProducerTest {
    @Autowired
    private Demo06Producer demo06Producer;

    @Test
    public void testSyncSendOrderly() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // 固定成 1024 ，方便我们测试是否发送到相同消息队列，
            // 3条消息都发送到了  queueId=1 队列ID为 1 的消息队列中
            int id = 1024;
            SendResult sendResult = demo06Producer.syncSendOrderly(id);
            log.info("[testSyncSendOrderly][发送编号:[{}] 发送结果:[{}]", id, sendResult);
        }
        new CountDownLatch(1).await();
    }

    @Test
    public void testAsyncSendOrderly() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = 1024;
            demo06Producer.asyncSendOrderly(id, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[testSyncSend][发送编号:[{}] 发送结果:[{}]", id, sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.info("[testSyncSend][发送编号:[{}] 发送异常:[{}]", id, throwable);
                }
            });

        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testOnewaySendOrderly() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = 1024;
            demo06Producer.onewaySendOrderly(id);
            log.info("[testOnewaySendOrderly][发送完成:[{}]]", id);
        }
        new CountDownLatch(1).await();
    }
}
