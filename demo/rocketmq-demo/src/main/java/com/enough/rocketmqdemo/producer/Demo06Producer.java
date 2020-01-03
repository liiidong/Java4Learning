package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo06Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 顺序消费消息生产者
 * @author: lidong
 * @create: 2020/01/03
 * <p>
 * 调用SendOrderly类型方法进行顺序发送消息
 */
@Component
public class Demo06Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     *
     * @param id
     * @return
     */
    public SendResult syncSendOrderly(Integer id) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        //第三个参数为hashKey，这里用id表示
        return rocketMQTemplate.syncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }

    /**
     * 异步发送消息
     *
     * @param id
     * @param callback
     */
    public void asyncSendOrderly(Integer id, SendCallback callback) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        //第三个参数为hashKey，这里用id表示
        rocketMQTemplate.asyncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id), callback);
    }

    /**
     * 单项发送消息
     *
     * @param id
     * @return
     */
    public void onewaySendOrderly(Integer id) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        //第三个参数为hashKey，这里用id表示
        rocketMQTemplate.sendOneWayOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }
}
