package com.futao.springboot.learn.rabbitmq.doc.limiting;

import com.futao.springboot.learn.rabbitmq.doc.limiting.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * @date 2020/4/17.
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "${app.rabbitmq.queue.default}")
    public void consumer(Article article) throws InterruptedException {
        log.info("{}", article.getId());
    }
}
