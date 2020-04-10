package com.futao.springboot.learn.rabbitmq.doc.deadletter;

import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * @date 2020/4/9.
 */
@Slf4j
@Configuration
public class AutoAckConsumer {

    /**
     * 正常用户队列消息监听消费者
     *
     * @param user
     */
    @RabbitListener(queues = "${app.rabbitmq.queue.user}")
    public void userConsumer(User user) {
        log.info("正常用户业务监听：接收到消息:[{}]", JSON.toJSONString(user));
        throw new RuntimeException("模拟发生异常");
    }

    /**
     * @param user
     */
    @RabbitListener(queues = "${app.rabbitmq.queue.user-dead-letter}")
    public void userDeadLetterConsumer(User user) {
        log.info("接收到死信消息并自动签收:[{}]", JSON.toJSONString(user));
    }
}
