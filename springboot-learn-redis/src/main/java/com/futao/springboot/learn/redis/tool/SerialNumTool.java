package com.futao.springboot.learn.redis.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author futao
 * @date 2020/4/20.
 */
@Slf4j
@Component
public class SerialNumTool implements ApplicationRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成yyyyMMdd＋6位流水号
     *
     * @return
     */
    public String next() {
        //获取当前年月日
        String yyyyMMdd = LocalDate.now(ZoneOffset.ofHours(8)).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String key = "redis:serialNum:order:" + yyyyMMdd;
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, "1", Duration.ofHours(25));
        if (lock != null && lock) {
            //当天第一个序列号
            return yyyyMMdd + fill(6, "1");
        }
        Long value = redisTemplate.opsForValue().increment(key);
        return yyyyMMdd + fill(6, String.valueOf(value));
    }


    private String fill(int maxLength, String currentStr) {
        if (StringUtils.isBlank(currentStr)) {
            return StringUtils.repeat("0", maxLength);
        }
        int length = currentStr.length();
        return StringUtils.repeat("0", maxLength - length) + currentStr;
    }

    @Autowired
    private SerialNumTool serialNumTool;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long millis = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int j = 0; j < 1000; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 200; i++) {
                    serialNumTool.next();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("{}{}", StringUtils.repeat("=", 40), System.currentTimeMillis() - millis);
    }
}
