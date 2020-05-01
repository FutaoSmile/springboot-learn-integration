package com.futao.springboot.learn.api.config;

import com.futao.springboot.learn.api.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 不会报错，但是容器中只有u1
 *
 * @author futao
 * @date 2020/4/29
 */
@Configuration
public class BookConfig {

    @Bean("u1")
    public Book u1() {
        System.out.println("-----1");
        return new Book("1", "a1", "n1", new Date());
    }

    @Bean("u1")
    public Book u2() {
        System.out.println("-----2");

        return new Book("2", "a2", "n2", new Date());
    }

    @Bean("u1")
    public Book u3() {
        System.out.println("-----3");

        return new Book("3", "a3", "n3", new Date());
    }

    @Bean("u1")
    public Book u4() {
        System.out.println("-----4");

        return new Book("4", "a4", "n4", new Date());
    }

}
