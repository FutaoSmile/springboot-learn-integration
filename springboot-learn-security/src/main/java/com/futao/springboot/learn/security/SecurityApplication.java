package com.futao.springboot.learn.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //开启基于注解的httpSecurity安全配置
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
