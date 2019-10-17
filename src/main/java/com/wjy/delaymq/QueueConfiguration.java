package com.wjy.delaymq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfiguration {

    /**
     * @Desc:消息队列app.queue.hello
     */
    @Bean
    public Queue helloQueue() {
        Queue queue = new Queue("app.queue.hello", true, false, false);
        return queue;
    }

    /**
     * 默认即时消息交换机
     */
    @Bean("defaultDirectExchange")
    public DirectExchange defaultDirectExchange() {
        return new DirectExchange("default.direct.exchange", true, false);
    }

    /**
     * @Desc:消息队列app.queue.hello绑定到默认队列上
     * 交换机匹配规则：app.queue.hello
     */
    @Bean
    public Binding helloBinding() {
        return BindingBuilder.bind(helloQueue()).to(defaultDirectExchange()).with("app.queue.hello");
    }

    /**
     * 配置延迟消息死信队列
     */
    @Bean
    public Queue defaultDeadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //设置交换机路由
        arguments.put("x-dead-letter-exchange", "default.direct.exchange");
        //设置转发队列名称
        arguments.put("x-dead-letter-routing-key", "default.repeat.trade.queue");
        Queue queue = new Queue("default.dead.letter.queue", true, false, false, arguments);
        return queue;
    }

    /**
    * @Desc:将延迟消息队列绑定到延迟交换机上
     * 交换机匹配规则：default.dead.letter.queue
    */
    @Bean
    public Binding defaultDeadLetterBinding() {
        Binding bind = BindingBuilder.bind(defaultDeadLetterQueue()).to(defaultDirectExchange()).with("default.dead.letter.queue");
        return bind;
    }


    /**
     * 配置转发消息队列default.repeat.trade.queue
     * @return
     */
    @Bean
    public Queue defaultRepeatTradeQueue() {
        Queue queue = new Queue("default.repeat.trade.queue", true, false, false);
        return queue;
    }

    /**
     * 转发队列和默认交换机的绑定;
     * 交换机匹配规则：default.repeat.trade.queue
     */
    @Bean
    public Binding defaultRepeatTradeBinding() {
        return BindingBuilder
                .bind(defaultRepeatTradeQueue())
                .to(defaultDirectExchange())
                .with("default.repeat.trade.queue");
    }


}
