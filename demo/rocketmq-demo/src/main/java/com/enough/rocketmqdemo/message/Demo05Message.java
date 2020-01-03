package com.enough.rocketmqdemo.message;

/**
 * @program: rocketmq-demo
 * @description: 广播消费消息对象
 * @author: lidong
 * @create: 2020/01/03
 */
public class Demo05Message {
    public static final String TOPIC = "DEMO_05";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
