package com.futao.springboot.learn.spring;

import com.futao.springboot.learn.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Slf4j
@SpringBootApplication
public class MySpringApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MySpringApplication.class, args);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
//            log.info(beanDefinitionName);
//            System.out.println(beanDefinitionName);
            String name = UserService.class.getName();
//            System.out.println(name);
            if (name.equalsIgnoreCase(beanDefinitionName)) {
                System.out.println(beanDefinitionName);
            }
        }
    }
}
