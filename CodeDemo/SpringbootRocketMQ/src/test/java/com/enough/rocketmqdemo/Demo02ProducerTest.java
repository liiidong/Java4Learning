package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @program: rocketmq-demo
 * @description: 批量发送消息02测试
 * @author: lidong
 * @create: 2020/01/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer demo02Producer;

    @Test
    public void testBatchSend() throws InterruptedException {
        List <Integer> ids = Arrays.asList(1, 2, 3);
        SendResult sendResult = demo02Producer.sendBatch(ids);
        log.info("[testBatchSend][发送线程编号：{}，发送结果：{}]", Thread.currentThread().getId(), sendResult);
        new CountDownLatch(1).await();
    }
}
