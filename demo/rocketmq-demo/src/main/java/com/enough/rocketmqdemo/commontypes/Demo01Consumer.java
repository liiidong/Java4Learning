package com.enough.rocketmqdemo.commontypes;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 消费者, 实现RocketMQListener接口来消费消息
 * @author: lidong
 * @create: 2020/01/02
 * <p>
 * 在类上，添加了 @RocketMQMessageListener 注解，声明消费的 Topic 是 "DEMO_01" ，消费者分组是 "demo01-consumer-group-DEMO_01" 。
 * 一般情况下，我们建议一个消费者分组，仅消费一个 Topic 。这样做会有两个好处：
 * 每个消费者分组职责单一，只消费一个 Topic 。
 * <p>
 * 每个消费者分组是独占一个线程池，这样能够保证多个 Topic 隔离在不同线程池，保证隔离性，从而避免一个 Topic 消费很慢，影响到另外的 Topic 的消费。
 * 实现 RocketMQListener 接口，在 T 泛型里，设置消费的消息对应的类。此处，我们就设置了 Demo01Message 类。
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC)
public class Demo01Consumer implements RocketMQListener <Demo01Message> {
    @Override
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), message);
    }
}
