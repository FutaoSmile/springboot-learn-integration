package com.futao.springboot.learn.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * Created on 2019-07-18.
 */
@Configuration
public class ActivemqConfig {

    @Bean("sapFeatureOptionRuleTopic")
    public ActiveMQTopic queue() {
        return new ActiveMQTopic("sapFeatureOptionRuleTopic");
    }

}
