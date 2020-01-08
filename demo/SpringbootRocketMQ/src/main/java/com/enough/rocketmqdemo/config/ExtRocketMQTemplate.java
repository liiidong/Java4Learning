package com.enough.rocketmqdemo.config;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * @program: rocketmq-demo
 * @description: 自定义扩展RocketMQTemplate
 * @author: lidong
 * @create: 2020/01/03
 *
 * 扩展完成后，可通过@Autowired或@Resource注入该对象进行使用
 */
@ExtRocketMQTemplateConfiguration(nameServer = "${demo.rocketmq.extNameServer:demo.rocketmq.name-server}")
public class ExtRocketMQTemplate extends RocketMQTemplate {
}
