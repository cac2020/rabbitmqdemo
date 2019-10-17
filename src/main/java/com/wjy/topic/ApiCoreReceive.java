package com.wjy.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreReceive {
    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void handle(String msg) {
        System.err.println("api.core receive message: "+msg);
    }
}
