package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo05Producer;
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
 * @description: 广播消费测试
 * @author: lidong
 * @create: 2020/01/03
 * <p>
 * 先启动test()方法，启动一个demo05消费者分组的consumer节点
 * 再启动bordercastSyncSendTest(),看到两个消费者都消费了该消息
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo05ProducerTest {

    @Autowired
    private Demo05Producer demo05Producer;

    @Test
    public void test() throws InterruptedException {
        log.info("启动一个demo05消费者分组的consumer节点");
        new CountDownLatch(1).await();
    }

    @Test
    public void bordercastSyncSendTest() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = demo05Producer.syncSend(id);
        log.info("[bordercastSyncSendTest][消息发送线程：{}，发送结果：{}]", Thread.currentThread().getId(), result);
        new CountDownLatch(1).await();
    }
}
