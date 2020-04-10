package com.futao.springboot.learn.rabbitmq.doc.deadletter;

import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * @date 2020/4/7.
 */
@Slf4j
@Component
public class DeadLetterSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Value("${app.rabbitmq.exchange.user}")
    private String userExchange;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    public void send(String exp) {

        User user = User.builder()
                .userName("天文")
                .address("浙江杭州")
                .birthday(LocalDate.now(ZoneOffset.ofHours(8)))
                .build();
        user.setId(atomicInteger.getAndIncrement() + "");
        log.info("消息投递...，id为：[{}]...指定的存活时长为:[{}]ms", user.getId(), exp);
        rabbitTemplate.convertAndSend(userExchange, "user.abc", user);
    }
}
