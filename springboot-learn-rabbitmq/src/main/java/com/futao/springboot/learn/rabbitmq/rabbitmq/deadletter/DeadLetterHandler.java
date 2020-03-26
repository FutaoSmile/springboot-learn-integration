package com.futao.springboot.learn.rabbitmq.rabbitmq.deadletter;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author futao
 * @date 2020/3/20.
 */
@Slf4j
@Component
public class DeadLetterHandler {

    @RabbitListener(queues = "user-dead-letter-queue")
    public void userDeadLetterHandler(String msg, Message message, Channel channel, @Headers Map<String, Object> map) {
        try {
            log.error("【死信队列消息】{}--------------------------------------------", msg);
            //可以在Message中看到失败原因，失败之前投递的exchange和queue
            log.error("【死信队列message】{}", JSON.toJSONString(message));
            log.error("【死信队列message.body】{}", JSON.toJSONString(new String(message.getBody())));
            log.error("【死信队列channel】{}", JSON.toJSONString(channel));
            log.error("【死信队列map】{}", JSON.toJSONString(map));
        } catch (Exception e) {

        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @RabbitListener(queues = "ttl-dead-letter-queue")
    public void ttlDeadLetterHandler(String msg, Message message, Channel channel, @Headers Map<String, Object> map) {
        try {
            log.error("【死信队列消息】--------------------------------------------{}", msg);
            //可以在Message中看到失败原因，失败之前投递的exchange和queue
            log.error("【死信队列message】{}", JSON.toJSONString(message));
            log.error("【死信队列message.body】{}", JSON.toJSONString(new String(message.getBody())));
            log.error("【死信队列channel】{}", JSON.toJSONString(channel));
            log.error("【死信队列map】{}", JSON.toJSONString(map));
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
