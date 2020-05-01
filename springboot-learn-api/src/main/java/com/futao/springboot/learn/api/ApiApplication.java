package com.futao.springboot.learn.api;

import com.futao.springboot.learn.api.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@EnableAsync
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApiApplication.class, args);


        System.out.println(context.getBean(Book.class));

        System.out.println(context.getBean("u1"));
//        System.out.println(context.getBean("u3"));
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("---beanDefinitionName" + beanDefinitionName);
        }
    }
}