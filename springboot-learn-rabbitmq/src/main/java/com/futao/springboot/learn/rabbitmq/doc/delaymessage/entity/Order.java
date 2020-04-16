package com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author futao
 * @date 2020/4/10.
 */
@Getter
@Setter
@Builder
@TableName("t_order")
public class Order {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 下单用户主键
     */
    @TableField("user_id")
    private String userId;

    /**
     * 订单状态
     *
     * @see OrderStatusEnum
     */
    @TableField("status")
    private int status;

    /**
     * 支付时间
     */
    @TableField("pay_date_time")
    private LocalDateTime payDateTime;

    /**
     * 订单过期时间
     */
    @TableField("expire_date_time")
    private LocalDateTime expireDateTime;

    /**
     * 下单时间
     */
    @TableField("create_date_time")
    private LocalDateTime createDateTime;
}



