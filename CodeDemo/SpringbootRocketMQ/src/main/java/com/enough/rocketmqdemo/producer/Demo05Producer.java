package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 广播消费消息生产者
 * @author: lidong
 * @create: 2020/01/03
 */
@Component
@Slf4j
public class Demo05Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     *
     * @param id
     * @return
     */
    public SendResult syncSend(Integer id) {
        Demo05Message message = new Demo05Message();
        message.setId(id);
        return rocketMQTemplate.syncSend(Demo05Message.TOPIC, message);
    }

}
