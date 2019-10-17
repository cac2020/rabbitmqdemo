package com.wjy.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
    * @Desc: 添加一个order()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order
    */
    public void order(String msg){
        System.err.println("api.payment.order send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    /**
     * @Desc: 添加一个orderQuery()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order.query
     */
    public void orderQuery(String msg){
        System.err.println("api.payment.order.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    /**
     * @Desc: 添加一个orderDetailQuery()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order.detail.query
     */
    public void orderDetailQuery(String msg){
        System.err.println("api.payment.order.detail.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }
}
