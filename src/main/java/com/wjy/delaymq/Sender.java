package com.wjy.delaymq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(QueueMessage message) {
        //即时消息
        if(message.getType() == 10){
            sendMessage(message.getExchange(),message.getQueueName(),message.getMessage());
        }
        //延时消息
        if(message.getType() == 20){
            sendTimeMessage(message);
        }
    }

    //发送即时消息;
    private void sendMessage(String exchange,String queueName,String msg){
        rabbitTemplate.convertAndSend(exchange,queueName, msg);
    }

    //发送延时消息;
    public void sendTimeMessage(QueueMessage message) {
        int seconds = message.getSeconds();
        // 直接发送，无需进入死信队列
        if(seconds <= 0){
            sendMessage(message.getExchange(),message.getQueueName(), message.getMessage());
        }else{
            //rabbit默认为毫秒级
            long times = seconds * 1000;
            //这里需要字符定义延时处理器;
            MessagePostProcessor processor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(times + "");
                    return message;
                }
            };
            //注意传送的消息必须是字串串或者 字节或者实现序列化的对象
            //否则报错：Execution of Rabbit message listener failed
            //改完后将之前的队列数据清除 否则还会报错
            rabbitTemplate.convertAndSend("default.direct.exchange","default.dead.letter.queue", "转发消息", processor);
        }
    }

}
