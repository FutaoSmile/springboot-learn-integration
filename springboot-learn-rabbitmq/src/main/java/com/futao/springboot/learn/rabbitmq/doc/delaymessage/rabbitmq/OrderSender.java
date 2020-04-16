package com.futao.springboot.learn.rabbitmq.doc.delaymessage.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author futao
 * @date 2020/4/14.
 */
@Slf4j
@Component
public class OrderSender {

    /**
     * 订单延迟时长
     */
    @Value("${app.rabbitmq.delay.order}")
    private Duration delayTime;

    /**
     * 订单延迟交换机名称
     */
    @Value("${app.rabbitmq.exchange.order.delay}")
    private String orderExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订单信息投递到延迟交换机
     *
     * @param orderId 订单主键
     */
    public void send(String orderId) {
        rabbitTemplate.convertAndSend(orderExchange, "order.delay.abc", orderId, message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            //设置消息的延迟投递时间
            messageProperties.setDelay((int) delayTime.toMillis());
            return message;
        });
        log.info("订单[{}]投递到MQ", orderId);
    }
}



