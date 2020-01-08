package com.enough.rocketmqdemo.message;

/**
 * @program: rocketmq-demo
 * @description: 定时消息对象
 * @author: lidong
 * @create: 2020/01/03
 */
public class Demo03Message {
    public static final String TOPIC = "DEMO_03";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
