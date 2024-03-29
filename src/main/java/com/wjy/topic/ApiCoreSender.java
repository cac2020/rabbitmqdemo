package com.wjy.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
    * @Desc: 发送消息至coreExchange交换机且routingKey为api.core.user
    */
    public void user(String msg){
        System.err.println("api.core.user send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    /**
     * @Desc: 发送消息至coreExchange交换机且routingKey为api.core.user.query
     */
    public void userQuery(String msg){
        System.err.println("api.core.user.query send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }
}
