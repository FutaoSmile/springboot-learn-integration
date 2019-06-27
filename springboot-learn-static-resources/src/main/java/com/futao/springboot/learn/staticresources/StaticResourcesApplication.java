package com.futao.springboot.learn.staticresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 静态资源位置及优先级(递减)
 * classpath:/META-INF/resources/
 * classpath:/resources/
 * classpath:/static/
 * classpath:/public/
 * /
 * <p>
 * # 通过配置定义:
 * spring.mvc.static-path-pattern=/resources/**
 * spring.resources.static-locations=classpath:/static/
 * <p>
 * # 通过Java编码定义
 * webMvcConfig配置
 */
@SpringBootApplication
public class StaticResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticResourcesApplication.class, args);
    }

}
