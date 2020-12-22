package com.futao.springboot.learn.rabbitmq.doc.limiting;

import com.futao.springboot.learn.rabbitmq.doc.limiting.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * @date 2020/4/17.
 */
@Slf4j
//@Component
public class DefaultSender implements ApplicationRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange.default}")
    private String defaultExchange;

    public void send() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                log.info("---");
                for (int j = 0; j < 10000; j++) {
                    rabbitTemplate.convertAndSend(defaultExchange, "default.key.abc", Article.defaultArticle());
                }
            });
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        send();
    }
}
