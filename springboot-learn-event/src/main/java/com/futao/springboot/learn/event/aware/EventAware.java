package com.futao.springboot.learn.event.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * @date 2020/5/1
 */
@Slf4j
@Component
public class EventAware implements ApplicationEventPublisherAware {
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        log.info("我倒想看看你是在什么时候被调用的");
    }
}
