package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo02Message;
import com.enough.rocketmqdemo.message.Demo03Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 定义消息生产者
 * @author: lidong
 * @create: 2020/01/03
 * <p>
 * 定时消息目前只支持[同步和异步]发送
 * <p>
 * 且定时消息目前只支持根据[延时等级]进行定时发送，不支持具体的定时时间
 */
@Component
@Slf4j
public class Demo03Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步定时发送消息
     *
     * @param id
     * @param delayLevel
     * @return
     */
    public SendResult syncSendDelay(Integer id, int delayLevel) {
        Demo03Message message = new Demo03Message();
        message.setId(id);
        return rocketMQTemplate.syncSend(Demo03Message.TOPIC, MessageBuilder.withPayload(message).build(), 30 * 1000L, delayLevel);
    }

    /**
     * 异步定时发送消息
     *
     * @param id
     * @param delayLevel
     * @param sendCallback
     */
    public void asynvSendDelay(Integer id, int delayLevel, SendCallback sendCallback) {
        Demo03Message message = new Demo03Message();
        message.setId(id);
        rocketMQTemplate.asyncSend(Demo02Message.TOPIC, MessageBuilder.withPayload(message).build(), sendCallback, 30 * 1000L, delayLevel);
    }
}
