package com.futao.springboot.learn.rabbitmq.rabbitmq.expiration;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author futao
 * @date 2020/3/26.
 */
@Component
public class Sender implements ApplicationRunner {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String expiration) {
        LocalDateTime now = LocalDateTime.now();
        rabbitTemplate.convertAndSend("expiration-exchange", "routing.key.abc", expiration, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                //如果5秒之后消息过期，则会被发送到死信队列
                messageProperties.setExpiration(expiration);
                return message;
            }
        }, new CorrelationData(now.format(DateTimeFormatter.BASIC_ISO_DATE)));
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        send("10000");
        send("5000");
        send("1000");
    }
}
