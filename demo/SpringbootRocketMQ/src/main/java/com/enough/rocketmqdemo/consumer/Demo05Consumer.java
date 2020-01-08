package com.enough.rocketmqdemo.consumer;

import com.enough.rocketmqdemo.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 广播消费消息消费者
 * @author: lidong
 * @create: 2020/01/03
 * <p>
 * 注意：消息类型messageModel设置为[BROADCASTING]广播模式
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "demo05-consumer-group" + Demo05Message.TOPIC, topic = Demo05Message.TOPIC, messageModel = MessageModel.BROADCASTING)
public class Demo05Consumer implements RocketMQListener <Demo05Message> {

    @Override
    public void onMessage(Demo05Message message) {
        log.info("[onMessage][消费线程编号：{}，消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
