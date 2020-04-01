package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息状态枚举
 *
 * @author futao
 * @date 2020/3/31.
 */
@Getter
@AllArgsConstructor
public enum MessageStatusEnum {

    /**
     * 1=发送中
     */
    SENDING(1, "发送中"),

    /**
     * 2=发送失败
     */
    SUCCESS(2, "发送成功"),

    /**
     * 3=发送失败
     */
    FAIL(3, "发送失败");

    private int status;
    private String description;
}
