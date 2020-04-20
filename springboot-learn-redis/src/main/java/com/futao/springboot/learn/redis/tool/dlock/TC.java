package com.futao.springboot.learn.redis.tool.dlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * @date 2020/4/20.
 */
@Component
public class TC {

    @Autowired
    private DistributedOnlyRunnerAop distributedOnlyRunnerAop;

    @Scheduled(cron = "0,5,10,15,20,25,30,35,40,45,50,55 * * * * ?")
    @DistributedOnlyRunner(groupName = "aM", expireTime = 5L, timeUnit = TimeUnit.SECONDS)
    public void aM() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
