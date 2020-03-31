package com.futao.springboot.learn.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Slf4j
@Service
public class UserService {

    public void sayHello() {
        log.info("{}", "hello from userService");
    }
}
