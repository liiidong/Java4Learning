package com.enough.rocketmqdemo.producer;

import com.enough.rocketmqdemo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @program: rocketmq-demo
 * @description: 事务消息生产者
 * @author: lidong
 * @create: 2020/01/07
 */
@Component
@Slf4j
public class Demo07Producer {
    /**
     * 定义事务生产者分组
     */
    private static final String TX_PRODUCER_GROUP = "demo07_producer_group";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送事务消息
     *
     * @param id
     * @return TransactionSendResult 继承了 SendResult，多封装了一层事务状态LocalTransactionState
     * <p>
     * LocalTransactionState（COMMIT_MESSAGE,ROLLBACK_MESSAGE,UNKNOW,）
     */
    public TransactionSendResult sendTransactionMessage(Integer id) {
        Demo07Message demo07Message = new Demo07Message();
        demo07Message.setId(id);
        Message message = MessageBuilder.withPayload(demo07Message).build();
        return rocketMQTemplate.sendMessageInTransaction(TX_PRODUCER_GROUP, Demo07Message.TOPIC, message, id);
    }

    /**
     * RocketMQ事务监听类
     * 事务分组同消息生产者一个分组
     */
    @RocketMQTransactionListener(txProducerGroup = TX_PRODUCER_GROUP)
    class TransactionListenerImpl implements RocketMQLocalTransactionListener {
        /**
         * 执行本地事务
         * <p>
         * 注意，这是一个模板方法。在调用这个方法之前，RocketMQTemplate 已经使用 Producer 发送了一条事务消息。
         * 然后根据该方法执行的返回的 RocketMQLocalTransactionState 结果，确定是提交还是回滚该事务消息。
         * <p>
         * 这里为了模拟 RocketMQ 回查 Producer 来获得事务消息的状态，所以返回了 RocketMQLocalTransactionState.UNKNOWN
         * 而具体应用中，需要人为编写代码去根据业务情况或异常捕捉情况来进行状态的返回
         *
         * @param msg
         * @param arg
         * @return
         */
        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            log.info("[executeLocalTransaction][执行本地事务，消息：{}，参数：{}]", msg, arg);
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        /**
         * 检查本地事务
         * <p>
         * 在事务消息长事件未被提交或回滚时，RocketMQ 会回查事务消息对应的生产者分组下的 Producer ，获得事务消息的状态。此时，该方法就会被调用。
         * <p>
         * 这里，直接返回 RocketMQLocalTransactionState.COMMIT
         *
         * @param msg
         * @return
         */
        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            log.info("[checkLocalTransaction][检查本地事务，消息：{}]", msg);
            return RocketMQLocalTransactionState.COMMIT;
        }
    }
}
