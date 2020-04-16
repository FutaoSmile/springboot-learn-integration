package com.futao.springboot.learn.rabbitmq.doc.delaymessage;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 队列，交换机定义与绑定
 * 延迟队列插件`rabbitmq-delayed-message-exchange`下载地址 https://github.com/rabbitmq/rabbitmq-delayed-message-exchange
 *
 * @author futao
 * @date 2020/4/10.
 */
@Configuration
public class Declare {

    /**
     * 订单队列 - 接收延迟投递的订单
     *
     * @param orderQueue 订单队列名称
     * @return
     */
    @Bean
    public Queue orderDelayQueue(@Value("${app.rabbitmq.queue.order.delay}") String orderQueue) {
        return QueueBuilder
                .durable(orderQueue)
                .build();
    }

    /**
     * 订单交换机-延迟交换机 - 消息延迟一定时间之后再投递到绑定的队列
     *
     * @param orderExchange 订单延迟交换机
     * @return
     */
    @Bean
    public Exchange orderDelayExchange(@Value("${app.rabbitmq.exchange.order.delay}") String orderExchange) {
        Map<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "topic");
        return new CustomExchange(orderExchange, "x-delayed-message", true, false, args);
    }

    /**
     * 订单队列-交换机 绑定
     *
     * @param orderQueue         订单队列
     * @param orderDelayExchange 订单交换机
     * @return
     */
    @Bean
    public Binding orderBinding(Queue orderDelayQueue, Exchange orderDelayExchange) {
        return BindingBuilder
                .bind(orderDelayQueue)
                .to(orderDelayExchange)
                .with("order.delay.*")
                .noargs();
    }

}
