package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo03Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 定时消息03消费者
 * @author: lidong
 * @create: 2020/01/03
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Demo03Message.TOPIC, consumerGroup = "demo03-consumer-group" + Demo03Message.TOPIC)
public class Demo03Consumer implements RocketMQListener <Demo03Message> {

    @Override
    public void onMessage(Demo03Message message) {
        log.info("[onMessage][消费线程编号：{}，消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
