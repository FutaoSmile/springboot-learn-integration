package com.futao.springboot.learn.enhance.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Slf4j
@RestController
public class InterceptorController {

    @GetMapping("/interceptor")
    public void interceptor() {
        log.info("interceptor run");
    }

    @GetMapping("/interceptorExclude")
    public void interceptorExclude() {
        log.info("interceptorExclude run");
    }
}
