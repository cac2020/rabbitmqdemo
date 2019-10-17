package com.wjy.headers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HeadersConfig {
    /**
    * @Desc: 配置一个routingKey为credit.bank的消息队列
    */
    @Bean
    public Queue creditBankQueue() {
        return new Queue("credit.bank");
    }

    /**
     * @Desc: 配置一个routingKey为credit.finance的消息队列
     */
    @Bean
    public Queue creditFinanceQueue() {
        return new Queue("credit.finance");
    }

    /**
     * @Desc: 配置一个creditBankExchange交换机
     */
    @Bean
    public HeadersExchange creditBankExchange() {
        return new HeadersExchange("creditBankExchange");
    }

    /**
     * @Desc: 配置一个creditFinanceExchange交换机
     */
    @Bean
    public HeadersExchange creditFinanceExchange() {
        return new HeadersExchange("creditFinanceExchange");
    }

    /**
     * @Desc: 配置一个routingKey为credit.bank的消息队列并绑定在creditBankExchange交换机上
     */
    @Bean
    public Binding bindingCreditAExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        //whereall 完全匹配
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
    }

    /**
    * @Desc: 配置一个routingKey为credit.finance的消息队列并绑定在creditFinanceExchange交换机上
    */
    @Bean
    public Binding bindingCreditBExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        //whereany 其中一项匹配即可
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerValues).match();
    }
}
