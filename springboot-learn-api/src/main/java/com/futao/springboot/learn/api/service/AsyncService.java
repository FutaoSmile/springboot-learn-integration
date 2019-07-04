package com.futao.springboot.learn.api.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019-07-04.
 */
@Slf4j
@Service
public class AsyncService {
    /**
     * 同步方法
     */
    @SneakyThrows
    public void sync() {
        TimeUnit.SECONDS.sleep(5);
        log.info("sync---");
    }

    /**
     * 异步方法
     */
    @Async
    @SneakyThrows
    public void async() {
        TimeUnit.SECONDS.sleep(5);
        log.info("async===");
    }
}
