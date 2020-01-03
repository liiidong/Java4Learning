package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 消费重试消息消费者
 * @author: lidong
 * @create: 2020/01/03
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Demo04Message.TOPIC, consumerGroup = "demo04-consumer-group" + Demo04Message.TOPIC)
public class Demo04Consumer implements RocketMQListener <Demo04Message> {
    @Override
    public void onMessage(Demo04Message message) {
        log.info("[onMessage][消费线程编号：{}，消息内容：{}]", Thread.currentThread().getId(), message);
        throw new RuntimeException("人为制造一个异常");
    }
}
