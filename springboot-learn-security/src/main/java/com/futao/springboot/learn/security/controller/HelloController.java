package com.futao.springboot.learn.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@RestController
public class HelloController {

    @Secured("ROLE_admin")  //ROLE_是必填的
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }


    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "admin hello world";
    }

    @PostAuthorize("hasAnyRole('admin','DBA','user')")
    @GetMapping("/db/hello")
    public String dBAHello() {
        return "DBA hello world";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "user hello world";
    }
}
