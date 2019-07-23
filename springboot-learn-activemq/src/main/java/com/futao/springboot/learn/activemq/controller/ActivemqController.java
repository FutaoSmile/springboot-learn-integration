package com.futao.springboot.learn.activemq.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-07-18.
 */
@Slf4j
@RestController
public class ActivemqController {

    @Resource
    private JmsTemplate jmsMessagingTemplate;

    @GetMapping("/send")
    public void send(@RequestBody JSONObject jsonObject) {
        jmsMessagingTemplate.convertAndSend("sapFeatureOptionRuleTopic", jsonObject);
        log.info("success");
    }
}
