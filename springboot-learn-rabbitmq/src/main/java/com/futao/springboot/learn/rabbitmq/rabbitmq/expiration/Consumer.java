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
public class Consumer {

    /**
     * 后面的消息仍然会被阻塞，直到最前面的msg过期
     *
     * @param data
     * @param channel
     * @param message
     * @throws InterruptedException
     * @throws IOException
     */
    @RabbitListener(queues = "expiration-queue")
    public void consumer(String data, Channel channel, Message message) throws InterruptedException, IOException {
        log.info("收到正常的消息[{}]，处理中...", data);
        Thread.sleep(10000L);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("处理完成");
    }
}
