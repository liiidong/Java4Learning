package com.enough.learn.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;

/**
 * @program: learn-redis
 * @description: Redis执行脚本单元测试
 * @author: lidong
 * @create: 2020/01/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScriptTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testScript() throws IOException {
        // 读取 脚本文件 内容
        String luaScriptConent = IOUtils.toString(getClass().getResourceAsStream("/lua/compareAndSet.lua"));
        // 创建redisScript 脚本对象，注意泛型类型前后一致性
        RedisScript <Long> redisScript = new DefaultRedisScript <>(luaScriptConent, Long.class);
        // 执行脚本文件
        // keys: Collections.singletonList("testScript")
        // args: 参数对象对应脚本文件中的Argv[]占位符情况
        Long result = stringRedisTemplate.execute(redisScript, Collections.singletonList("testScript"), "testScript", "testScript2");
        log.info("[testScript][脚本执行结果：{}]", result);
    }
}
