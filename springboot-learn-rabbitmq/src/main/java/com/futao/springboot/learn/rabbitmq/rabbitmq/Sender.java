package com.futao.springboot.learn.rabbitmq.rabbitmq;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.futao.springboot.learn.rabbitmq.model.UserModel;
import com.futao.springboot.learn.rabbitmq.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * @date 2020/3/14.
 */
@Slf4j
//@Component
public class Sender implements ApplicationRunner {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    AtomicInteger atomicInteger = new AtomicInteger(1);

    ExecutorService executorService = Executors.newFixedThreadPool(10, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "threadNum" + atomicInteger.getAndIncrement());
        }
    });

    public void userSender() throws InterruptedException {
        for (int j = 0; j < 1; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {

                    UserModel userModel = UserModel.builder()
                            .userName(CommonUtil.getRandomJianHan(3) + i)
                            .age(i)
                            .birthday(LocalDate.now(ZoneOffset.ofHours(8)))
                            .id(IdUtil.simpleUUID())
                            .createDateTime(LocalDateTime.now())
                            .build();

                    int finalI = i;
                    rabbitTemplate.convertAndSend(
                            "ttl-exchange",
                            "",
                            JSON.toJSONString(userModel),
                            //在消息发送之前应用于它的处理器
                            message -> {
                                int i1 = 1000 * (10 - finalI);
                                log.info("set age {} ttl {}", finalI, i1);
                                message.getMessageProperties().setDelay(finalI);
                                return message;
                            },
                            new CorrelationData(String.valueOf(userModel.getId()))
                    );
//                    log.info("send{}", i);
                }
            });
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userSender();
    }
}
