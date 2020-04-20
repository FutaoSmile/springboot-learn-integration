package com.futao.springboot.learn.redis.tool.dlock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author futao
 * Created on 2019/10/15.
 */
@Slf4j
@Aspect
@Component
public class DistributedOnlyRunnerAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Around(value = "@annotation(distributedOnlyRunner)")
    public Object enhance(ProceedingJoinPoint proceedingJoinPoint, DistributedOnlyRunner distributedOnlyRunner) throws Throwable {
        long startTime = System.currentTimeMillis();
        String repeat = StringUtils.repeat("=", 30);
        log.info("{}定时任务开始争抢执行权{}", repeat, repeat);
        String redisKey = "distributedOnlyRunner:" + distributedOnlyRunner.groupName();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, "Lock", distributedOnlyRunner.expireTime(), distributedOnlyRunner.timeUnit());
        Object result = null;
        if (Objects.equals(lock, Boolean.TRUE)) {
            //成功获取锁
            log.info("{}获得执行权，开始执行{}", repeat, repeat);
            try {
                result = proceedingJoinPoint.proceed();
            } finally {
                stringRedisTemplate.delete(redisKey);
                log.info("{}任务执行完毕，耗时[{}]ms{}", repeat, System.currentTimeMillis() - startTime, repeat);
            }
        } else {
            //未获得锁
            log.info("{}未获得执行权，任务结束{}", repeat, repeat);
        }
        return result;
    }
}
