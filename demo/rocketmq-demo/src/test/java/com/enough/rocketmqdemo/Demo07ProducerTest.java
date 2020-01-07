package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @program: rocketmq-demo
 * @description: 事务消息单元测试
 * @author: lidong
 * @create: 2020/01/07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo07ProducerTest {

    @Autowired
    private Demo07Producer demo07Producer;

    @Test
    public void sendTransactionMessageTest() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        TransactionSendResult result = demo07Producer.sendTransactionMessage(id);
        log.info("[sendTransactionMessageTest][消息发送线程编号：{}，发送结果：{}]", Thread.currentThread().getId(), result);
        new CountDownLatch(1).await();
    }
}
