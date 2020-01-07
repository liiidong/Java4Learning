package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 事务消息消费者
 * @author: lidong
 * @create: 2020/01/07
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = Demo07Message.TOPIC, consumerGroup = "demo07_consumer_group" + Demo07Message.TOPIC)
public class Demo07Consumer implements RocketMQListener <Demo07Message> {

    @Override
    public void onMessage(Demo07Message message) {
        log.info("[onMessage][事务消息消费线程编号：{}，消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
