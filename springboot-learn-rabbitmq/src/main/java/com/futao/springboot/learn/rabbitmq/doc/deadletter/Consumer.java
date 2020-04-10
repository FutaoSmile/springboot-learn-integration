package com.futao.springboot.learn.rabbitmq.doc.deadletter;

import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author futao
 * @date 2020/4/9.
 */
@Slf4j
//@Component
public class Consumer {

    /**
     * 正常用户队列消息监听消费者
     *
     * @param user
     * @param message
     * @param channel
     */
//    @RabbitListener(queues = "${app.rabbitmq.queue.user}")
    public void userConsumer(User user, Message message, Channel channel) {
        log.info("正常用户业务监听：接收到消息:[{}]", JSON.toJSONString(user));
        try {
            //参数为：消息的DeliveryTag，是否批量拒绝，是否重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            log.info("拒绝签收...消息的路由键为:[{}]", message.getMessageProperties().getReceivedRoutingKey());
        } catch (IOException e) {
            log.error("消息拒绝签收失败", e);
        }
    }

    /**
     * @param user
     * @param message
     * @param channel
     */
    @RabbitListener(queues = "${app.rabbitmq.queue.user-dead-letter}")
    public void userDeadLetterConsumer(User user, Message message, Channel channel) {
        log.info("接收到死信消息:[{}]", JSON.toJSONString(user));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("\n死信队列签收消息....消息路由键为:[{}]", message.getMessageProperties().getReceivedRoutingKey());
        } catch (IOException e) {
            log.error("死信队列消息签收失败", e);
        }
    }
}
