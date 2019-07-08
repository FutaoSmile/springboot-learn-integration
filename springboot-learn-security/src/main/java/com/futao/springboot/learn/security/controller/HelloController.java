package com.futao.springboot.learn.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }


    @GetMapping("/admin/hello")
    public String adminHello() {
        return "admin hello world";
    }

    @GetMapping("/db/hello")
    public String dBAHello() {
        return "DBA hello world";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "user hello world";
    }
}
