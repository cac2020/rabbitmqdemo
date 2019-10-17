package com.wjy.delaymq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "default.repeat.trade.queue")
public class TradeRecever {
    @Autowired
    private Sender sender;

    @RabbitHandler
    public void process(String content) {
        System.err.println("-----------延时结束--------------"+content);
        QueueMessage message = new QueueMessage("app.queue.hello", "转发消息...");
        message.setType(10);
        sender.send(message);
    }
}
