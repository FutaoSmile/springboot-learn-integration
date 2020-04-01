package com.futao.springboot.learn.rabbitmq.rabbitmq.expiration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 * @author futao
 * @date 2020/3/26.
 */
//@Configuration
public class ExpirationDefinition {

    @Bean
    public Queue expirationQueue() {
        return QueueBuilder
                .durable("expiration-queue")
                //声明该队列的死信消息发送到的 交换机
                .withArgument("x-dead-letter-exchange", "common-dead-letter-exchange")
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "expiration-dead-letter-routing-key")
                .autoDelete()
                .build();
    }

    @Bean
    public Exchange expirationExchange() {
        return ExchangeBuilder
                .topicExchange("expiration-exchange")
                .autoDelete()
                .build();
    }


    @Bean
    public Binding expirationBinding(Queue expirationQueue, Exchange expirationExchange) {
        return BindingBuilder
                .bind(expirationQueue)
                .to(expirationExchange)
                .with("routing.key.*")
                .noargs();
    }

    @Bean
    public Queue expirationDeadLetterQueue() {
        return QueueBuilder
                .durable("expiration-dead-letter-queue")
                .autoDelete()
                .build();
    }

    @Bean
    public Binding expirationDeadLetterBinding(Queue expirationDeadLetterQueue, Exchange deadLetterExchange) {
        return BindingBuilder
                .bind(expirationDeadLetterQueue)
                .to(deadLetterExchange)
                .with("expiration-dead-letter-routing-key")
                .noargs();
    }

}
