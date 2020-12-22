package com.futao.springboot.learn.rabbitmq.doc.limiting;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * @date 2020/4/17.
 */
@Configuration
public class DefaultDeclare {

    @Bean
    public Queue defaultQueue(@Value("${app.rabbitmq.queue.default}") String defaultQueue) {
        return QueueBuilder
                .durable(defaultQueue)
                .build();
    }

    @Bean
    public Exchange defaultExchange(@Value("${app.rabbitmq.exchange.default}") String defaultExchange) {
        return ExchangeBuilder
                .topicExchange(defaultExchange)
                .durable(true)
                .build();
    }


    @Bean
    public Binding defaultBuilding(Queue defaultQueue, Exchange defaultExchange) {
        return BindingBuilder
                .bind(defaultQueue)
                .to(defaultExchange)
                .with("default.key.*")
                .noargs();
    }
}
