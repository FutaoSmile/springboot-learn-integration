package com.futao.springboot.learn.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author futao
 * Created on 2019-07-18.
 */
@EnableJms
@SpringBootApplication
public class ActivemqApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }
}
