package com.futao.springboot.learn.rabbitmq.rabbitmq.deadletter;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.model.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author futao
 * @date 2020/3/19.
 */
@Slf4j
@Component
public class DeadLetterTest implements ApplicationRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rabbitTemplate.convertAndSend(
                "order-exchange",
                "",
                JSON.toJSONString(Order.builder().id("1").address("上海市").build()), new CorrelationData(IdUtil.simpleUUID())
        );
    }


    @RabbitListener(queues = "order-queue", concurrency = "3-5")
    public void orderReceiver(String body, Channel channel, Message message) throws IOException {
        log.info("开始消费[{}]", body);

//        throw new RuntimeException("");
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}
