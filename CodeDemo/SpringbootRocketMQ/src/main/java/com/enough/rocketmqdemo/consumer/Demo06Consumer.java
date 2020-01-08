package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo06Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 顺序消费消息消费者
 * @author: lidong
 * @create: 2020/01/03
 *
 * 注意：消费类型consumeMode的值设置为ORDERLY，按顺序消费
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Demo06Message.TOPIC, consumerGroup = "demo06-consumer-group" + Demo06Message.TOPIC, consumeMode = ConsumeMode.ORDERLY)
public class Demo06Consumer implements RocketMQListener <Demo06Message> {
    @Override
    public void onMessage(Demo06Message message) {
        log.info("[onMessage][消费线程编号：{}，消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
