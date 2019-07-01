package com.futao.springboot.learn.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@MapperScan("com.futao.springboot.learn.mybatis.mapper")   //指明mapper所在的包(方式1)，或者在每个mapper类上加上@Mapper注解
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
