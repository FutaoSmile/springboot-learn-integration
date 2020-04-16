package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.controller;

import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.User;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.rabbitmq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/4/1.
 */
@RequestMapping("/user")
//@RestController
public class UserController {

    @Autowired
    private Sender sender;

    @RequestMapping("/send")
    public void send() {
        sender.send(User
                .builder()
                .userName("天文")
                .birthday(LocalDate.of(1995, 1, 31))
                .address("浙江杭州")
                .build());
    }
}
