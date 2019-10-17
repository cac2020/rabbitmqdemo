package com.wjy.ack;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "queue-test")
public class Comsumer {

    @RabbitHandler
    public void process(String msg,Message message, Channel channel) throws IOException {
        try {
            // 采用手动应答模式, 手动确认应答更为安全稳定
            /*channel.basicAck(deliveryTag,ack)
            deliveryTag-当前消息的类似编号的号码，服务端为每一个消息生成的类似编号的号码
            ack-false只确认当前一个消息收到，true确认所有consumer获得的消息
            */
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            System.err.println("receive: " + new String(message.getBody()));
        }
        catch (Exception e){
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            //拒绝这条消息
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
