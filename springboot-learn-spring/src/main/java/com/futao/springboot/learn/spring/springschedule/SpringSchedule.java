package com.futao.springboot.learn.spring.springschedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * @date 2020/4/28
 */
@Slf4j
@Component
public class SpringSchedule {

    @Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
    public void cron() {
        log.info("--");
    }
}
