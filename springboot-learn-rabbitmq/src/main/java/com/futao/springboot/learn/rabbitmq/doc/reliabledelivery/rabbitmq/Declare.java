package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * RabbitMQ队列定义与绑定
 *
 * @author futao
 * @date 2020/3/31.
 */
//@Configuration
public class Declare {

    @Bean
    public Queue userQueue(@Value("${app.rabbitmq.queue.user}") String userQueueName) {
        return QueueBuilder
                .durable(userQueueName)
                //.withArgument("x-max-length", 2)
                .build();
    }

    @Bean
    public Exchange userExchange(@Value("${app.rabbitmq.exchange.user}") String userExchangeName) {
        return ExchangeBuilder
                .topicExchange(userExchangeName)
                .durable(true)
                .build();
    }

    @Bean
    public Binding userBinding(Queue userQueue, Exchange userExchange) {
        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with("user.*")
                .noargs();
    }
}
