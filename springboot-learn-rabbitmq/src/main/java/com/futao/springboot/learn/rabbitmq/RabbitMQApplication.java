package com.futao.springboot.learn.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * rabbitMQ中上一次unAck的消息，会在下一次消费者连上的时候再次投递，再次重新消费
 *
 * @author futao
 * @date 2020/3/14.
 */
@Slf4j
@SpringBootApplication
public class RabbitMQApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class, args);
    }

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //设置消费者在断开与RabbitMQ的连接之后自动重新连接
        cachingConnectionFactory.getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);

        //消息投递成功与否的监听，可以用来保证消息100%投递到rabbitMQ。（如果某条消息（通过id判定)在一定时间内未收到该回调，则重发该消息)
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息投递成功");
            } else {
                log.info("消息投递失败");
            }
            log.info("消息相关数据，消息ID，correlationData:{}", JSON.toJSONString(correlationData));
            log.info("cause:{}", cause);
        });

    }
}
