package com.futao.springboot.learn.rabbitmq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.dao.UserMapper;
import com.futao.springboot.learn.rabbitmq.model.UserModel;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
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
    @RabbitListener(queues = "user-queue")
    public void userReceiver(String body, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("开始消费[{}]", body);
        //如开启了手动ACK，则需要这样设置
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        userMapper.insert(JSON.parseObject(body, UserModel.class));
    }
}
