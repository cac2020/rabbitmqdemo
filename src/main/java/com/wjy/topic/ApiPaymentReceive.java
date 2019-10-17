package com.wjy.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentReceive {
    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void handle(String msg) {
        System.err.println("api.payment.order receive message: "+msg);
    }
}
