package com.futao.springboot.learn.rabbitmq.rabbitmq.delay;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author futao
 * @date 2020/3/26.
 */
@Configuration
public class Binding {

    public static final String DELAY_QUEUE = "delay-queue";
    public static final String DELAY_EXCHANGE = "delay-exchange";

    @Bean
    public Queue delayQueueA() {
        return QueueBuilder
                .durable(DELAY_QUEUE)
                .autoDelete()
                .build();
    }

    @Bean
    public Exchange delayExchangeA() {
        HashMap<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-delayed-type", "topic");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, true, arguments);
    }

    @Bean
    public org.springframework.amqp.core.Binding bindingA(Queue delayQueueA, Exchange delayExchangeA) {
        return BindingBuilder
                .bind(delayQueueA)
                .to(delayExchangeA)
                .with("delayed.routing.key.*")
                .noargs();
    }
}
