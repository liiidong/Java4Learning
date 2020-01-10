package com.enough.learn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: learn-redis
 * @description: 消息消费监听类单元测试
 * @author: lidong
 * @create: 2020/01/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PubSubTest {

    private static final String TOPIC = "pubsubTest";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testPub() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // convertAndSend 发送消息
            stringRedisTemplate.convertAndSend(TOPIC, "hello redisMsg" + i);
            // 阻塞1秒，保证消息被消费
            Thread.sleep(1000);
        }
    }
}
