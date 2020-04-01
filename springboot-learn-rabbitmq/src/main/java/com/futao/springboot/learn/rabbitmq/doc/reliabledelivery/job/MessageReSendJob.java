package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.mapper.MessageMapper;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.Message;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.enums.MessageStatusEnum;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author futao
 * @date 2020/4/1.
 */
@Slf4j
@Component
public class MessageReSendJob extends IJobHandler {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageMapper messageMapper;

    @Autowired
    private MessageReSendJob messageReSendJob;

    /**
     * 最大重试次数
     */
    @Value("${app.rabbitmq.retry.max-retry-times}")
    private int retryTimes;

    /**
     * 重试时间间隔
     */
    @Value("${app.rabbitmq.retry.retry-interval}")
    private Duration retryInterval;

    /**
     * 批量从数据库中读取的消息
     */
    private static final int PAGE_SIZE = 100;


    @XxlJob(value = "MessageReSendJob", init = "jobHandlerInit", destroy = "jobHandlerDestroy")
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("开始扫描需要进行重试投递的消息");
        XxlJobLogger.log("开始扫描需要进行重试投递的消息");
        service(1);
        log.info("扫描需要进行重试投递的消息任务结束，耗时[{}]ms", System.currentTimeMillis() - startTime);
        XxlJobLogger.log("扫描需要进行重试投递的消息任务结束，耗时[{}]ms", System.currentTimeMillis() - startTime);
        return ReturnT.SUCCESS;
    }

    public void service(int pageNum) {
        IPage<Message> messageIPage = messageMapper.selectPage(new Page<>(pageNum, PAGE_SIZE),
                Wrappers.<Message>lambdaQuery()
                        //发送中的消息
                        .eq(Message::getStatus, MessageStatusEnum.SENDING.getStatus())
                        //已到达下次发送时间
                        .le(Message::getNextRetryDateTime, LocalDateTime.now(ZoneOffset.ofHours(8)))
        );
        List<Message> messages = messageIPage.getRecords();
        messages.forEach(message -> {
            if (retryTimes <= message.getRetryTimes()) {
                //已达到最大投递次数，将消息设置为投递失败
                messageMapper.update(null, Wrappers.<Message>lambdaUpdate().set(Message::getStatus, MessageStatusEnum.FAIL.getStatus()).eq(Message::getId, message.getId()));
            } else {
                messageReSendJob.reSend(message);
            }
        });
        if (PAGE_SIZE == messages.size()) {
            service(++pageNum);
        }
    }

    /**
     * 重新投递消息
     *
     * @param message
     */
//    @Transactional(rollbackFor = Exception.class)
    public void reSend(Message message) {
        messageMapper.update(null,
                Wrappers.<Message>lambdaUpdate()
                        .set(Message::getRetryTimes, message.getRetryTimes() + 1)
                        .set(Message::getNextRetryDateTime, LocalDateTime.now(ZoneOffset.ofHours(8)).plus(retryInterval))
                        .eq(Message::getId, message.getId())
        );
        //再次投递
        rabbitTemplate.convertAndSend(message.getExchangeName(), message.getRoutingKey(), message.getMsgData(), new CorrelationData(message.getId()));
    }

    public void jobHandlerInit() {
        log.info("before job execute...");
        XxlJobLogger.log("before job handler init...");
    }

    public void jobHandlerDestroy() {
        log.info("after job execute...");
        XxlJobLogger.log("after job execute...");
    }
}
