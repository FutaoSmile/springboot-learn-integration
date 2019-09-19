package com.futao.springboot.learn.redis.controller;

import com.futao.springboot.learn.redis.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-07-05.
 */
@RequestMapping("/book")
@RestController
@Slf4j
public class BookController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public void redis() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("name", "三国演义");
        String name = opsForValue.get("name");
        log.info("name:{}", name);

        ValueOperations valueOperations = redisTemplate.opsForValue();
        Book book = new Book(123, "钢铁是怎样炼成的", "王宝刚");
        valueOperations.set("book1", book);

        Book bookResult = (Book) valueOperations.get("book1");
        log.info("bookResult:{}", bookResult);

        SetOperations opsForSet = redisTemplate.opsForSet();
    }
}
