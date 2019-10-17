package com.wjy.direct;


import com.wjy.mojo.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Desc: 生产者
*/
@Component
public class MqSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
    * @Desc: 将消息发送至默认的交换机且routingKey为q_hello
    */
    public void send() {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        System.err.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }

    /**
     * @Desc: 将消息发送至默认的交换机且routingKey为q_hello
     */
    public void send(String i) {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + i + " " + date;
        System.err.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }

    /**
     * @Desc: 将发送对象
     */
    public void sender(Order order){
        System.err.println("notify.refund send message: "+order);
        rabbitTemplate.convertAndSend("notify.refund", order);
    }

    /**
     * @Desc: 测试RPC
     */
    public void sender(String orderId){
        System.err.println("query.order send message: "+orderId);
        Order order = (Order) rabbitTemplate.convertSendAndReceive("query.order", orderId);
        System.err.println("query.order return message: "+order);
    }
}
