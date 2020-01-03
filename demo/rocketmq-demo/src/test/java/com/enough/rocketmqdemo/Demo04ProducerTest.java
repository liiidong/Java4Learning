package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @program: rocketmq-demo
 * @description: 消费重试机制测试类
 * @author: lidong
 * @create: 2020/01/03
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
public class Demo04ProducerTest {

    @Autowired
    private Demo04Producer demo04Producer;

    @Test
    public void syncSendRetryTest() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = demo04Producer.syncSend(id);
        log.info("[syncSendRetryTest][发送消息线程编号：{}，发送结果：{}]", Thread.currentThread().getId(), result);
        new CountDownLatch(1).await();
    }
}
