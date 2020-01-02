package com.enough.rocketmqdemo.commontypes;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 集群消费测试
 * @author: lidong
 * @create: 2020/01/02
 * <p>
 * 整体和 「3.8 Demo01Consumer」 是一致的，主要有两个差异点，也是为什么我们又额外创建了这个消费者的原因。
 * 集群消费（Clustering）：集群消费模式下，相同 Consumer Group 的每个 Consumer 实例平均分摊消息。
 * <p>
 * 也就是说，如果我们发送一条 Topic 为 "DEMO_01" 的消息，可以分别被 "demo01-A-consumer-group-DEMO_01" 和 "demo01-consumer-group-DEMO_01" 都消费一次。
 * 但是，如果我们启动两个该示例的实例，则消费者分组 "demo01-A-consumer-group-DEMO_01" 和 "demo01-consumer-group-DEMO_01" 都会有多个 Consumer 示例。
 * 此时，我们再发送一条 Topic 为 "DEMO_01" 的消息，只会被 "demo01-A-consumer-group-DEMO_01" 的一个 Consumer 消费一次，
 * 也同样只会被 "demo01-A-consumer-group-DEMO_01" 的一个 Consumer 消费一次。
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-A-customer-group" + Demo01Message.TOPIC)
public class Demo01AConsumer implements RocketMQListener <MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        log.info("[omMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), message);
    }
}
