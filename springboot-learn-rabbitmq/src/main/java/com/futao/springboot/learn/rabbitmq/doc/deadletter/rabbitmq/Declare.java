package com.futao.springboot.learn.rabbitmq.doc.deadletter.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列与交换机定义与绑定
 *
 * @author futao
 * @date 2020/4/7.
 */
@Configuration
public class Declare {

    /**
     * 用户队列
     *
     * @param userQueueName 用户队列名
     * @return
     */
    @Bean
    public Queue userQueue(@Value("${app.rabbitmq.queue.user}") String userQueueName,
                           @Value("${app.rabbitmq.exchange.common-dead-letter}") String commonDeadLetterExchange) {
        return QueueBuilder
                .durable(userQueueName)
                //声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
                .withArgument("x-dead-letter-exchange", commonDeadLetterExchange)
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "user-dead-letter-routing-key")
                //该队列的消息的过期时间-超过这个时间还未被消费则路由到死信队列
                .withArgument("x-message-ttl", 5000)
                //队列最大消息数量
                .withArgument("x-max-length", 2)
                .build();
    }

    /**
     * 用户交换机
     *
     * @param userExchangeName 用户交换机名
     * @return
     */
    @Bean
    public Exchange userExchange(@Value("${app.rabbitmq.exchange.user}") String userExchangeName) {
        return ExchangeBuilder
                .topicExchange(userExchangeName)
                .durable(true)
                .build();
    }

    /**
     * 用户队列与交换机绑定
     *
     * @param userQueue    用户队列名
     * @param userExchange 用户交换机名
     * @return
     */
    @Bean
    public Binding userBinding(Queue userQueue, Exchange userExchange) {
        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with("user.*")
                .noargs();
    }

    /**
     * 死信交换机
     *
     * @param commonDeadLetterExchange 通用死信交换机名
     * @return
     */
    @Bean
    public Exchange commonDeadLetterExchange(@Value("${app.rabbitmq.exchange.common-dead-letter}") String commonDeadLetterExchange) {
        return ExchangeBuilder
                .topicExchange(commonDeadLetterExchange)
                .durable(true)
                .build();
    }


    /**
     * 用户队列的死信消息 路由的队列
     * 用户队列user-queue的死信投递到死信交换机`common-dead-letter-exchange`后再投递到该队列
     * 用这个队列来接收user-queue的死信消息
     *
     * @return
     */
    @Bean
    public Queue userDeadLetterQueue(@Value("${app.rabbitmq.queue.user-dead-letter}") String userDeadLetterQueue) {
        return QueueBuilder
                .durable(userDeadLetterQueue)
                .build();
    }

    /**
     * 死信队列绑定死信交换机
     *
     * @param userDeadLetterQueue      user-queue对应的死信队列
     * @param commonDeadLetterExchange 通用死信交换机
     * @return
     */
    @Bean
    public Binding userDeadLetterBinding(Queue userDeadLetterQueue, Exchange commonDeadLetterExchange) {
        return BindingBuilder
                .bind(userDeadLetterQueue)
                .to(commonDeadLetterExchange)
                .with("user-dead-letter-routing-key")
                .noargs();
    }

}
