package com.futao.springboot.learn.event.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * @date 2020/5/1
 */
@Slf4j
@Configuration
public class Config implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        return Executors.newFixedThreadPool(10, r -> new Thread(r, "异步线程-" + atomicInteger.getAndIncrement()));
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                log.error("发生异常,method:[{}],params:[{}]", JSON.toJSONString(method), JSON.toJSONString(params), ex);
            }
        };
    }
}
