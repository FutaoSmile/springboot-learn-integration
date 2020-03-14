package com.futao.springboot.learn.rabbitmq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/3/14.
 */
@Slf4j
@Component
public class Sender implements ApplicationRunner {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void userSender() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            UserModel userModel = UserModel.builder()
                    .userName("futao" + i)
                    .age(i)
                    .birthday(LocalDate.now())
                    .id(i)
                    .build();
            rabbitTemplate.convertAndSend("user-exchange", "", JSON.toJSONString(userModel), new CorrelationData(String.valueOf(userModel.getId())));
            Thread.sleep(100);
            log.info("send{}", i);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                try {
//                    userSender();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }
}
