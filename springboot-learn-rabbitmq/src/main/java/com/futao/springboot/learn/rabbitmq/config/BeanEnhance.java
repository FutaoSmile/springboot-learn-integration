package com.futao.springboot.learn.rabbitmq.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.mapper.MessageMapper;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.Message;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.enums.MessageStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Bean增强
 * 【严重警告】: 不可在该类中注入Bean，被注入的Bean不会被BeanPostProcessor增强，造成误伤。
 * 必须通过容器来获取需要注入的Bean
 *
 * @author futao
 * @date 2020/3/20.
 */
@Slf4j
@Component
public class BeanEnhance implements BeanPostProcessor {

//    @Resource
//    private MessageMapper messageMapper;

    /**
     * 消息的最大重试次数
     */
    @Value("${app.rabbitmq.retry.max-retry-times}")
    private int maxRetryTimes;

    /**
     * 每次重试时间间隔
     */
    @Value("${app.rabbitmq.retry.retry-interval}")
    private Duration retryInterval;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private BeanEnhance enhance;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //增强RabbitTemplate
        if (RabbitTemplate.class.equals(bean.getClass())) {
            //消息投递成功与否的监听，可以用来保证消息100%投递到rabbitMQ。（如果某条消息（通过id判定)在一定时间内未收到该回调，则重发该消息)
            //需要设置 publisher-confirms: true
//            ((RabbitTemplate) bean).setConfirmCallback((correlationData, ack, cause) -> {
//                String correlationDataId = correlationData.getId();
//                if (ack) {
//                    //ACK
//                    log.debug("消息[{}]投递成功，将DB中的消息状态设置为投递成功", correlationDataId);
//                    ApplicationContextHolder.getBean(MessageMapper.class).update(null,
//                            Wrappers.<Message>lambdaUpdate()
//                                    .set(Message::getStatus, MessageStatusEnum.SUCCESS.getStatus())
//                                    .set(Message::getUpdateDateTime, LocalDateTime.now(ZoneOffset.ofHours(8)))
//                                    .eq(Message::getId, correlationDataId)
//                    );
//                } else {
//                    log.debug("消息[{}]投递失败,cause:{}", correlationDataId, cause);
//                    //NACK，消息重发
//                    ApplicationContextHolder.getBean(BeanEnhance.class).reSend(correlationDataId);
//                }
//            });

            //消息路由失败的回调--需要设置   publisher-returns: true 并且   template: mandatory: true 否则rabbit将丢弃该条消息
//            ((RabbitTemplate) bean).setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//                log.warn("消息路由失败回调...做一些补偿或者记录.............................................");
//                log.warn("message：{}", message);
//                log.warn("replyCode：{}", replyCode);
//                log.warn("replyText：{}", replyText);
//                log.warn("exchange：{}", exchange);
//                log.warn("routingKey：{}", routingKey);
//            });
        }
        return bean;
    }


    /**
     * NACK时进行消息重发
     *
     * @param correlationDataId
     */
    @Transactional(rollbackFor = Exception.class)
    public void reSend(String correlationDataId) {
        Message message = ApplicationContextHolder.getBean(MessageMapper.class).selectById(correlationDataId);
        if (message.getRetryTimes() < maxRetryTimes) {
            //进行重试
            ApplicationContextHolder.getBean(RabbitTemplate.class).convertAndSend(message.getExchangeName(), message.getRoutingKey(), message.getMsgData(), new CorrelationData(correlationDataId));
            //更新DB消息状态
            ApplicationContextHolder.getBean(MessageMapper.class).update(null,
                    Wrappers.<Message>lambdaUpdate()
                            .set(Message::getStatus, MessageStatusEnum.SENDING.getStatus())
                            .set(Message::getNextRetryDateTime, LocalDateTime.now(ZoneOffset.ofHours(8)).plus(retryInterval))
                            .set(Message::getRetryTimes, message.getRetryTimes() + 1)
                            .eq(Message::getId, correlationDataId)
            );
        }
    }
}
