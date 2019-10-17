package com.wjy.direct;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration这个注解是必须的，保证在基本类实例化之前该类已经被实例化
@Configuration
public class RabbitConfig {

    /**
    * @Desc:  配置一个消息队列（routingKey=q_hello）
    */
    @Bean
    public Queue queue() {
        return new Queue("q_hello");
    }

    /**
     * @Desc:  配置一个消息队列（routingKey=notify.refund）
     */
    @Bean
    public Queue refundNotifyQueue() {
        return new Queue("notify.refund");
    }

    /**
     * @Desc:  配置一个消息队列（routingKey=query.order） 测试RPC
     */
    @Bean
    public Queue queryOrderQueue() {
        return new Queue("query.order");
    }
}
