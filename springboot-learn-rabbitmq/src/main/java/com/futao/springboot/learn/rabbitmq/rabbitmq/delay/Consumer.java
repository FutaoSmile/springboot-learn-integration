package com.futao.springboot.learn.rabbitmq.rabbitmq.delay;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/3/26.
 */
@Slf4j
@Component
public class Consumer {

    @RabbitHandler
    @RabbitListener(queues = Binding.DELAY_QUEUE)
    public void consumer(LocalDateTime data, Channel channel, Message message) throws IOException {
        log.info("接收到消息[{}]", data);
        System.out.println("-----");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
