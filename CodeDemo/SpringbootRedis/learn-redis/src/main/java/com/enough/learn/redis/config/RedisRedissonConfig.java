package com.enough.learn.redis.config;

import com.enough.learn.redis.listeners.ChannelTopicMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @program: learn-redis
 * @description: redis配置
 * @author: lidong
 * @create: 2019/11/15
 */
@Slf4j
public class RedisRedissonConfig {

    /**
     * 初始化RedisTemplate
     *
     * @param factory redis连接工厂，这里我们指定的是Jedis连接方式
     * @return
     */
    @Bean
    public RedisTemplate <String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate <String, Object> template = new RedisTemplate <>();

        // 设置开启事务支持
        // redis事务同spring事务一样，将当前connection和当前请求的线程进行绑定，后面关于此线程的所有操作都使用绑定的这个connection，达到事务统一性的效果
        // redis事务的回滚或提交，最后交给spring去做
        // 方法或接口通过 【@Transaction】注解 开启事务
        template.setEnableTransactionSupport(true);

        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        template.setConnectionFactory(factory);

        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());

        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }

    /**
     * 初始化Redis消息监听容器
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
        // 实例化我们的ChannelTopicMessageListener对象
        ChannelTopicMessageListener messageListener = new ChannelTopicMessageListener();
        // 实例化RedisMessageListenerContainer对象
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 设置redis连接信息
        container.setConnectionFactory(factory);
        // 将我们的消息监听对象设置给redis消息容器
        container.addMessageListener(messageListener, new ChannelTopic("pubsubTest"));
        return container;
    }
}
