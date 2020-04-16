package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.mapper.MessageMapper;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.Message;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.User;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.enums.MessageStatusEnum;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author futao
 * @date 2020/3/31.
 */
//@Component
public class Sender {

    @Value("${app.rabbitmq.retry.retry-interval}")
    private Duration retryInterval;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageMapper messageMapper;

    @Value("${app.rabbitmq.exchange.user}")
    private String userExchangeName;

    public void send(User user) {
        Message message = Message.builder()
                .msgData(JSON.toJSONString(user))
                .exchangeName(userExchangeName)
                .routingKey("user.abc")
                .status(MessageStatusEnum.SENDING.getStatus())
                //下次重试时间
                .nextRetryDateTime(LocalDateTime.now(ZoneOffset.ofHours(8)).plus(retryInterval))
                .retryTimes(0)
                .build();
        //消息落库
        messageMapper.insert(
                message
        );
        CorrelationData correlationData = new CorrelationData(message.getId());
        //消息投递到MQ
        rabbitTemplate.convertAndSend(userExchangeName, "user.abc", JSON.toJSONString(user), correlationData);
    }

}
