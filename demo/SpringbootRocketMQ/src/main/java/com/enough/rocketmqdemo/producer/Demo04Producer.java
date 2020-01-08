package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo04Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 消费重试消息生产者
 * @author: lidong
 * @create: 2020/01/03
 */
@Component
public class Demo04Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 普通的同步发送消息
     *
     * @param id
     * @return
     */
    public SendResult syncSend(Integer id) {
        Demo04Message demo04Message = new Demo04Message();
        demo04Message.setId(id);
        return rocketMQTemplate.syncSend(Demo04Message.TOPIC, demo04Message);
    }
}
