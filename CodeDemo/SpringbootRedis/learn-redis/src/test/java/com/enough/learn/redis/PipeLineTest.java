package com.enough.learn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: learn-redis
 * @description: Redis批量操作测试
 * @author: lidong
 * @create: 2020/01/08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipeLineTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PIPELINE_TEST_KEY = "pipeLineTest:%d";

    @Test
    public void pipeLineTest() {
        List <Object> results = stringRedisTemplate.executePipelined(new RedisCallback <Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                // set操作，注意方法参数均为byte[]
                for (int i = 0; i < 3; i++) {
                    redisConnection.set(String.format(PIPELINE_TEST_KEY, i).getBytes(), String.valueOf(i).getBytes());
                }
                // get操作，注意方法参数均为byte[]
                for (int i = 0; i < 3; i++) {
                    redisConnection.get(String.format(PIPELINE_TEST_KEY, i).getBytes());
                }
                // 一定要返回null
                //executePipelined(RedisCallback<?> action, @Nullable RedisSerializer<?> resultSerializer)方法内部实现决定了返回值必须是null
                return null;
            }
        });
        // 这里我们使用的是StringRedisTemplate对象，所以 results 会被序列化为 String字符串格式
        log.info("[pipeLineTest][redis批量操作结果：{}]", results);
    }
}
