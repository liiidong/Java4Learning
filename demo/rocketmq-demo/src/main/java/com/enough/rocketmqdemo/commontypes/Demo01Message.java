package com.enough.rocketmqdemo.commontypes;

/**
 * @program: rocketmq-demo
 * @description: 测试消息实体对象
 * @author: lidong
 * @create: 2020/01/02
 */
public class Demo01Message {
    /**
     * TOPIC 静态属性，我们设置该消息类对应 Topic 为 "DEMO_01"
     */
    public static final String TOPIC = "DEMO_01";

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
