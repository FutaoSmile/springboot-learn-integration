package com.futao.springboot.learn.event.service;

import cn.hutool.core.util.RandomUtil;
import com.futao.springboot.learn.event.eventmodel.UserRegisterEvent;
import com.futao.springboot.learn.event.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * @author futao
 * @date 2020/5/1
 */
@Slf4j
@Service
public class UserService implements ApplicationRunner {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void register() {
        User futao = User.builder()
                .birthday(LocalDate.now(ZoneOffset.ofHours(8)))
                .fullName("futao")
                .id(RandomUtil.simpleUUID())
                .build();
        log.info("\n\n发布用户注册事件。。。");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(futao));
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            this.register();
            Thread.sleep(3000L);
        }
    }
}
