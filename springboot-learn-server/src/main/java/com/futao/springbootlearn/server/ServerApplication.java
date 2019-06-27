package com.futao.springbootlearn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@RequestMapping("/server")
@RestController
@SpringBwootApplication
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ServerApplication.class, args);
        System.out.println("----------success:" + run.getClass().getName());
    }


    @GetMapping("/")
    public String server() {
        return this.getClass().getName();
    }
}
