package com.futao.springboot.learn.redis.set;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * @date 2020/3/16.
 */
@Component
public class SetTest implements ApplicationRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SET_NAME = "springboot:learn:set";

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AtomicInteger i = new AtomicInteger();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            String key = SET_NAME + DateUtil.format(new Date(), "yyyyMMdd");
            Long add = redisTemplate.opsForSet().add(key, String.valueOf(i.incrementAndGet()));
            redisTemplate.expireAt(key, DateUtil.endOfDay(new Date()));
            System.out.println(key + " : " + i + " : " + add);
        }, 0, 1L, TimeUnit.SECONDS);
//        Long add = redisTemplate.opsForSet().add(SET_NAME, "123");
//        Long add1 = redisTemplate.opsForSet().add(SET_NAME, "1234");
//        Long add2 = redisTemplate.opsForSet().add(SET_NAME, "12345", "1", "2", "3");
//
//        redisTemplate.expireAt(SET_NAME, DateUtil.endOfDay(new Date()));
//
//
//        System.out.println(add);
//        System.out.println(add1);
//        System.out.println(add2);
    }
}
