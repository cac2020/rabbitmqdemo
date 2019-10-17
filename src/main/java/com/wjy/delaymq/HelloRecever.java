package com.wjy.delaymq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "app.queue.hello")
public class HelloRecever {

    @RabbitHandler
    public void process(String content) {

        System.err.println("hello 接受消息：" + content);
    }
}
