package com.futao.springboot.learn.redis.tool.dlock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式唯一执行器
 * 控制在多实例的情况下，只有一台机器在执行任务，前提：连接的是同一个redis
 *
 * @author futao
 * Created on 2019/10/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DistributedOnlyRunner {
    /**
     * 组别，相同组别内的任务只会有一个实例在执行
     *
     * @return
     */
    String groupName();

    /**
     * 过期时间（毫秒）
     *
     * @return
     */
    long expireTime();

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timeUnit();
}
