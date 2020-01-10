package com.enough.learn.redis.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @program: learn-redis
 * @description: 消息消费监听类
 * @author: lidong
 * @create: 2020/01/10
 */
@Slf4j
public class ChannelTopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("[收到 PatternTopic：{} 消息][线程编号：{}，消息内容：{}，]", new String(pattern), Thread.currentThread().getId(), message);
    }
}
