package com.futao.springboot.learn.rabbitmq.rabbitmq;

import com.futao.springboot.learn.rabbitmq.dao.UserMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 手动签收ack
 *
 * @author futao
 * @date 2020/3/14.
 */
@Slf4j
@Component
public class Receiver {

    @Resource
    private UserMapper userMapper;

    @RabbitHandler
    @RabbitListener(queues = "ttl-queue")
    public void userReceiver(String body, Channel channel, @Headers Map<String, Object> headers, Message message) throws IOException, InterruptedException {
        log.info("user开始消费[{}]", body);

//        Thread.sleep(1000L);
        //手动签收
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);


        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("{}", deliveryTag);
        //long deliveryTag, boolean multiple是否批量, boolean requeue是否重回队列-并且在头部
        //requeue=true，消息会被放在最前面。requeue=false，消息会放在最后面)
//        throw new RuntimeException("--");
//        channel.basicNack(deliveryTag, false, false);
//        userMapper.insert(JSON.parseObject(body, UserModel.class));
    }


    @RabbitListener(queues = "order-queue", concurrency = "3-5")
    public void orderReceiver(String msg, Channel channel, @Headers Map<String, Object> headers, Message message) throws IOException, InterruptedException {
        log.info("收到order消息[{}]", msg);
        Thread.sleep(3000L);
//        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }


}
