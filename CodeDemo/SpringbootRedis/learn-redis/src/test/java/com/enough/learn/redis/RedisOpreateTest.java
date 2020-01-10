package com.enough.learn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: learn-redis
 * @description: Redis操作测试
 * @author: lidong
 * @create: 2020/01/08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisOpreateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PIPELINE_TEST_KEY = "pipeLineTest:%d";

    /**
     * 批量操作测试
     */
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

    /**
     * redis事务测试: 适用于一个RedisConnection的情况下
     * 一个请求对应一个线程，将connection绑定在此线程上，后面针对connection的事务操作都从该线程获取，保证获取到的是同一个connection，达到事务的一致性
     */
    @Test
    public void redisTransactionTest() {
        // 开启事务
        stringRedisTemplate.setEnableTransactionSupport(true);
        stringRedisTemplate.multi();
        stringRedisTemplate.opsForValue().set("hello", "world");
        stringRedisTemplate.opsForValue().set("hello", "java");
        stringRedisTemplate.opsForValue().set("hi", "redis");
        log.info("[redisTransactionTest][执行结果：{}]", stringRedisTemplate.exec());

    }

    /**
     * SessionCallback
     * 使用 Redis Connection 连接池的时候，使用SessionCallback 保证所有的操作的事务都在一个session中进行
     */
    @Test
    public void sessionCallbackTest() {
        String result = stringRedisTemplate.execute(new SessionCallback <String>() {
            /**
             * Executes all the given operations inside the same session.
             *
             * @param operations Redis operations
             * @return return value
             */
            @Override
            public <K, V> String execute(RedisOperations <K, V> operations) throws DataAccessException {
                for (int i = 0; i < 10; i++) {
                    stringRedisTemplate.opsForValue().set(String.format("sessionCallbackTest:%d", i), "helloSessionCall");
                }
                return stringRedisTemplate.opsForValue().get(String.format("sessionCallbackTest:%d", 0));
            }
        });
        log.info("[sessionCallbackTest][执行结果：{}]", result);
    }


}
