package com.enough.learn.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: learn-redis
 * @description:
 * @author: lidong
 * @create: 2020/01/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("redisson", "helloRedisson");
    }
}
