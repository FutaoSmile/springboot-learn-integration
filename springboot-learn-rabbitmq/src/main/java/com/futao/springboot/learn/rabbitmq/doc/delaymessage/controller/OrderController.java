package com.futao.springboot.learn.rabbitmq.doc.delaymessage.controller;

import com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity.Order;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity.OrderStatusEnum;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.mapper.OrderMapper;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.rabbitmq.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 订单接口
 *
 * @author futao
 * @date 2020/4/14.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private OrderSender orderSender;

    /**
     * 下单
     */
    @PostMapping
    public void add() {
        Order order = Order
                .builder()
                .userId("futao")
                //待支付
                .status(OrderStatusEnum.TO_BE_PAID.getStatus())
                .createDateTime(LocalDateTime.now(ZoneOffset.ofHours(8)))
                .build();
        //订单入库
        orderMapper.insert(order);
        //投递延迟消息
        orderSender.send(order.getId());
    }
}


