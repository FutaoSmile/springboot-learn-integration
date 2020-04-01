package com.futao.springboot.learn.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * rabbitMQ中上一次unAck的消息，会在下一次消费者连上的时候再次投递，再次重新消费
 * <p>
 * ********************************************************************************************************
 * 如何保证消息的100%投递成功：
 * <p>
 * 1.给每条发送到MQ的消息设置ID
 * 2. 开启RabbitMQ消息投递回调setConfirmCallback()。从回调的返回值中取到消息的ID。
 * 3. 根据ID判断哪些消息投递成功了，哪些消息没有收到回调通知。
 * 4. （用一张表记录消息发送记录，并用定时任务扫描，超过指定的时间，则认认为消息投递失败) 如果在某段时间内，还没有收到通知，则认为该消息投递失败，再次往MQ发送该消息。
 * 5. 因为存在消息投递成功，但是回调失败的场景，所以存在消息重复投递问题。所以消费者要自己实现消息的幂等。
 *
 * <p>
 * <p>
 * *********************************************************************************************************
 *
 * @author futao
 * @date 2020/3/14.
 */
@MapperScan({"com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.mapper"})
@SpringBootApplication
public class RabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class, args);
    }
}
