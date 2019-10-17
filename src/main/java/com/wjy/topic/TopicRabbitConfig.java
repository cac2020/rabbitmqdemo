package com.wjy.topic;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    final static String message = "api.core";
    final static String messages = "api.payment";

    /**
     * 配置一个routingKey为api.core的消息队列
     */
    @Bean
    public Queue coreQueue() {
        return new Queue(TopicRabbitConfig.message);
    }

    /**
    * @Desc: 配置一个routingKey为api.payment的消息队列
    */
    @Bean
    public Queue paymentQueue() {
        return new Queue(TopicRabbitConfig.messages);
    }

    /**
     * @Desc: coreExchange交换机
     */
    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    /**
     * @Desc: paymentExchange交换机
     */
    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    /**
     * 配置一个routingKey为api.core的消息队列并绑定在coreExchange交换机上（交换机的匹配规则为api.core.*）
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    /**
     * @Desc: 配置一个routingKey为api.payment的消息队列并绑定在paymentExchange交换机上（交换机的匹配规则为api.payment.#）
     */
    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }
}
