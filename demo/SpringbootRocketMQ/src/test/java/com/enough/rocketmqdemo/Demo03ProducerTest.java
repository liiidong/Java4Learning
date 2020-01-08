package com.enough.rocketmqdemo;

import com.enough.rocketmqdemo.producer.Demo03Producer;
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
 * @description: 定时消息单元测试
 * @author: lidong
 * @create: 2020/01/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo03ProducerTest {

    @Autowired
    private Demo03Producer demo03Producer;

    @Test
    public void testSyncDelaySend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        // 延迟 10s 后被消费
        SendResult sendResult = demo03Producer.syncSendDelay(id, 3);
        log.info("[testSyncDelaySend][发送线程编号：{}，发送结果：{}]", Thread.currentThread().getId(), sendResult);
        new CountDownLatch(1).await();
    }

}
