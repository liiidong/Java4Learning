package com.enough.rocketmqdemo.message;

/**
 * @program: rocketmq-demo
 * @description: 批量发送消息使用
 * @author: lidong
 * @create: 2020/01/03
 */
public class Demo02Message {
    public static final String TOPIC = "DEMO_02";

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
