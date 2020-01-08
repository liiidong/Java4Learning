package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 批量发送消息消费者
 * @author: lidong
 * @create: 2020/01/03
 * <p>
 * 虽然Demo02是批量生产消息，但是这里可以逐一消费
 */
@Component
@RocketMQMessageListener(topic = Demo02Message.TOPIC, consumerGroup = "demo02-customer-group" + Demo02Message.TOPIC)
@Slf4j
public class Demo02Consumer implements RocketMQListener <Demo02Message> {

    @Override
    public void onMessage(Demo02Message message) {
        log.info("[omMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), message);
    }
}
