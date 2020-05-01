package com.futao.springboot.learn.event.eventlistener.user.register;

import com.alibaba.fastjson.JSON;
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
public class SendMsg implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        User source = event.getSource();
        log.info("接收到用户注册的事件，触发发送短信业务...[{}]", JSON.toJSONString(source));
    }
}
