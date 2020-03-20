package com.futao.springboot.learn.rabbitmq.rabbitmq.deadletter;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author futao
 * @date 2020/3/20.
 */
@Slf4j
@Component
public class DeadLetterHandler {

    @RabbitListener(queues = "user-dead-letter-queue")
    public void userDeadLetterHandler(String msg, Message message, Channel channel) {
        try {
            log.error("【死信队列消息】{}", msg);
        } catch (Exception e) {

        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
