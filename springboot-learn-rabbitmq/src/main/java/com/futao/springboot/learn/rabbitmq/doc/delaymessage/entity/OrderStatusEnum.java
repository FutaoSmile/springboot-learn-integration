package com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author futao
 * @date 2020/4/10.
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * 100=待支付
     */
    TO_BE_PAID(100, "待支付"),

    /**
     * 200=已过期
     */
    EXPIRED(200, "已过期"),

    /**
     * 300=已支付
     */
    PAID(300, "已支付");

    /**
     * 订单状态
     */
    private final int status;

    /**
     * 描述
     */
    private final String description;

}


