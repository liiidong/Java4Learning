package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo02Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: rocketmq-demo
 * @description: 批量发送消息生产者
 * @author: lidong
 * @create: 2020/01/03
 */
@Component
public class Demo02Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult sendBatch(List <Integer> ids) {
        // 创建消息集合
        List <Message> messageList = new ArrayList <>(ids.size());
        for (Integer id : ids) {
            Demo02Message demo02Message = new Demo02Message();
            demo02Message.setId(id);
            // 这里使用MessageBuilder进行消息类型的转换
            messageList.add(MessageBuilder.withPayload(demo02Message).build());
        }
        // 同步批量发送消息
        return rocketMQTemplate.syncSend(Demo02Message.TOPIC, messageList, 30 * 1000L);
    }

}
