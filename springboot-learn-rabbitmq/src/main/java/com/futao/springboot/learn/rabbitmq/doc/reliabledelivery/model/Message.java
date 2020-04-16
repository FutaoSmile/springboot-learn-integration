package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.base.IdTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * 消息发送历史
 *
 * @author futao
 * @date 2020/3/31.
 */
@Getter
@Setter
@Builder
@TableName("message")
public class Message extends IdTimeEntity {

    @Tolerate
    public Message() {
    }

    /**
     * 消息承载的业务数据
     */
    @TableField("msg_data")
    private String msgData;


    /**
     * 交换机名称
     */
    @TableField("exchange_name")
    private String exchangeName;

    /**
     * 路由键
     */
    @TableField("routing_key")
    private String routingKey;

    /**
     * 消息状态
     *
     * @see com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.enums.MessageStatusEnum
     */
    @TableField("status")
    private int status;

    /**
     * 重试次数
     */
    @TableField("retry_times")
    private int retryTimes;

    /**
     * 下一次重试时间
     */
    @TableField("next_retry_date_time")
    private LocalDateTime nextRetryDateTime;

}
