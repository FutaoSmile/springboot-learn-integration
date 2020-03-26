package com.futao.springboot.learn.rabbitmq.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * @date 2020/3/20.
 */
@Slf4j
@Component
public class BeanEnhance implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (CachingConnectionFactory.class.equals(bean.getClass())) {
            //设置消费者在断开与RabbitMQ的连接之后自动重新连接
            ((CachingConnectionFactory) bean).getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);
        }

        if (RabbitTemplate.class.equals(bean.getClass())) {
            //消息投递成功与否的监听，可以用来保证消息100%投递到rabbitMQ。（如果某条消息（通过id判定)在一定时间内未收到该回调，则重发该消息)
            //需要设置 publisher-confirms: true
            ((RabbitTemplate) bean).setConfirmCallback((correlationData, ack, cause) -> {
                if (ack) {
                    log.debug("消息投递成功");
                } else {
                    log.debug("消息投递失败");
                }
                log.debug("消息相关数据，消息ID，correlationData:{}", JSON.toJSONString(correlationData));
                log.debug("cause:{}", cause);
            });

            //消息路由失败的回调--需要设置   publisher-returns: true 并且   template: mandatory: true 否则rabbit将丢弃该条消息
            ((RabbitTemplate) bean).setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                log.warn("消息路由失败回调...做一些补偿或者记录.............................................");
                log.warn("message{}", message);
                log.warn("replyCode{}", replyCode);
                log.warn("replyText{}", replyText);
                log.warn("exchange{}", exchange);
                log.warn("routingKey{}", routingKey);
            });

            System.out.println("---" + ((RabbitTemplate) bean).getMessageConverter());

        }
        return bean;
    }
}
