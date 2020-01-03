package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 生产者
 * @author: lidong
 * @create: 2020/01/02
 */
@Component
public class Demo01Producer {

    /**
     * 使用 RocketMQ-Spring 封装提供的RocketMQTemplate进行消息发送
     */
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     *
     * @param id
     * @return
     */
    public SendResult syncSend(int id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC, message);
    }

    /**
     * 异步发送消息
     * 异步发送是指发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式。
     * MQ 的异步发送，需要用户实现异步发送回调接口（SendCallback）。
     * 消息发送方在发送了一条消息后，不需要等待服务器响应即可返回，进行第二条消息发送。
     * 发送方通过回调接口接收服务器响应，并对响应结果进行处理。
     *
     * @param id
     * @param callback 发送回调，成功（包含SendResult）/失败
     */
    public void asyncSend(int id, SendCallback callback) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }

    /**
     * 单项发送
     * 单向（Oneway）发送特点为发送方只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答。
     * 此方式发送消息的过程耗时非常短，一般在微秒级别
     *
     * @param id
     */
    public void onewaySend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC, message);
    }
}
