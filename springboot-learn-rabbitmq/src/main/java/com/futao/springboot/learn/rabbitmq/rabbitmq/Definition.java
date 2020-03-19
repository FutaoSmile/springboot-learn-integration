package com.futao.springboot.learn.rabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Exchange与Queue绑定
 * <p>
 * -----死信队列DeadLetter https://www.cnblogs.com/mfrank/p/11184929.html
 *
 * @author futao
 * @date 2020/3/14.
 */
@Component
public class Definition {


    //-----------user start--------------------------------------------------------------------------------

    @Bean
    public Exchange userExchange() {
        return ExchangeBuilder
                .fanoutExchange("user-exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Queue userQueue() {
        return QueueBuilder
                .durable("user-queue")
                //声明该队列的死信消息发送到的 交换机
                .withArgument("x-dead-letter-exchange", "common-dead-letter-exchange")
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "user-dead-letter-routing-key")
                //队列消息的TTL
//                .withArgument("x-message-ttl", 5000)
                .autoDelete()
                .build();
    }

    @Bean
    public Binding userBind(
            @Qualifier("userQueue") Queue userQueue,
            @Qualifier("userExchange") Exchange userExchange
    ) {
        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with("")
                .noargs();
    }

    //-----------user end--------------------------------------------------------------------------------


    //-----------order start--------------------------------------------------------------------------------

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder
                .fanoutExchange("order-exchange")
                .durable(true)
                .build();
    }


    @Bean
    public Queue orderQueue() {
        return QueueBuilder
                .durable("order-queue")
                //声明该队列的死信消息发送到的 交换机
                .withArgument("x-dead-letter-exchange", "common-dead-letter-exchange")
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "order-dead-letter-routing-key")
                //队列消息的TTL
//                .withArgument("x-message-ttl", 10000)
                .autoDelete()
                .build();
    }

    @Bean
    public Binding orderBinding(
            @Qualifier("orderExchange") Exchange exchange,
            @Qualifier("orderQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("")
                .noargs();
    }

    //-----------order end--------------------------------------------------------------------------------


    //-----------dead letter start--------------------------------------------------------------------------------


    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder
                .directExchange("common-dead-letter-exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Queue userDeadLetterQueue() {
        return QueueBuilder
                .durable("user-dead-letter-queue")
                .build();
    }

    @Bean
    public Binding userDeadLetterBinding(
            @Qualifier("userQueue") Queue userQueue,
            @Qualifier("deadLetterExchange") Exchange exchange
    ) {
        return BindingBuilder
                .bind(userQueue)
                .to(exchange)
                .with("user-dead-letter-routing-key")
                .noargs();
    }


    @Bean
    public Queue orderDeadLetterQueue() {
        return QueueBuilder
                .durable("order-dead-letter-queue")
                .build();
    }

    @Bean
    public Binding orderDeadLetterBinding(
            @Qualifier("orderQueue") Queue orderQueue,
            @Qualifier("deadLetterExchange") Exchange exchange
    ) {
        return BindingBuilder
                .bind(orderQueue)
                .to(exchange)
                //指定路由键
                .with("order-dead-letter-routing-key")
                .noargs();
    }

    //-----------dead letter end--------------------------------------------------------------------------------

}
