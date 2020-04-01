package com.futao.springboot.learn.rabbitmq.rabbitmq.expiration;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author futao
 * @date 2020/3/26.
 */
@Slf4j
//@Component
public class DeadLetterConsumer {

    @RabbitListener(queues = "expiration-dead-letter-queue")
    public void consumer(String data, Channel channel, Message message) throws IOException {
        log.info("接收到死信消息[{}]", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
