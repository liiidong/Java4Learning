package com.enough.rocketmqdemo.message;

/**
 * @program: rocketmq-demo
 * @description: 顺序消费消息对象
 * @author: lidong
 * @create: 2020/01/03
 */
public class Demo06Message {
    public static final String TOPIC = "DEMO_06";

    /**
     * 编号
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
