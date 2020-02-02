package com.futao.springboot.learn.mybatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 可以在TypeHandler中自定义对于某些类型的转换与处理
 *
 * @author futao
 * Created on 2019-07-01.
 */
@PropertySource("test.yml")
//@ImportResource("classpath*:testYml.yml")   //只支持xml文件
//@MapperScan("com.futao.springboot.learn.mybatis.mapper")   //指明mapper所在的包(方式1)，或者在每个mapper类上加上@Mapper注解
@SpringBootApplication
public class MybatisApplication implements ApplicationRunner {


    @Value("${appli}")
    private String appli;

    @Value("${testYml}")
    private String testYml;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("appli" + appli);
        System.out.println("testYml" + testYml);
    }
}
