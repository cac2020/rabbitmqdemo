package com.wjy.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Desc:  消费者2
 */
@Component
@RabbitListener(queues = "q_hello")
public class Receiver2 {
    /**
     * @Desc: 监听routingKey为nq_hello的队列消息
     */
    @RabbitHandler
    public void process(String hello) {
        System.err.println("Receiver2  : " + hello);
    }
}
