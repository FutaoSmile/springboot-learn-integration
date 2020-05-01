package com.futao.springboot.learn.event.eventlistener.user.register;


import com.futao.springboot.learn.event.eventmodel.UserRegisterEvent;
import com.futao.springboot.learn.event.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * @date 2020/5/1
 */
@Slf4j
@Configuration
public class InitUserScore implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        User source = event.getSource();
        log.info("接收到用户注册事件，触发初始化用户账户积分事件[{}]", source);
    }
}
