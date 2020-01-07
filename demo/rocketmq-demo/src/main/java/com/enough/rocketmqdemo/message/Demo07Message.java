package com.enough.rocketmqdemo.message;

/**
 * @program: rocketmq-demo
 * @description: 事务消息
 * @author: lidong
 * @create: 2020/01/07
 */
public class Demo07Message {

    public static final String TOPIC = "DEMO_07";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
