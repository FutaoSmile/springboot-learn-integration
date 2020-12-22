package com.futao.springboot.learn.rabbitmq.doc.delaymessage.rabbitmq;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity.Order;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.entity.OrderStatusEnum;
import com.futao.springboot.learn.rabbitmq.doc.delaymessage.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import static com.caucho.services.server.ServiceContext.getRequest;

/**
 * 延迟订单监听
 *
 * @author futao
 * @date 2020/4/14.
 */
@Slf4j
@Component
public class OrderConsumer {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 延迟订单监听，设置订单为已过期
     *
     * @param orderId
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = "${app.rabbitmq.queue.order.delay}")
    public void consumer(String orderId, Channel channel, Message message) throws IOException, InterruptedException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        log.info("消费者接收到延迟订单[{}]", orderId);
        //将订单状态设置成已超时过期
        orderMapper.update(null,
                Wrappers.<Order>lambdaUpdate()
                        .eq(Order::getId, orderId)
                        //待支付状态
                        .eq(Order::getStatus, OrderStatusEnum.TO_BE_PAID.getStatus())
                        //设置成已过期
                        .set(Order::getStatus, OrderStatusEnum.EXPIRED.getStatus())
                        //设置过期时间
                        .set(Order::getExpireDateTime, LocalDateTime.now(ZoneOffset.ofHours(8)))
        );
        TimeUnit.SECONDS.sleep(10);
        log.info("订单业务处理结束.....进行消息ack签收");
        //msg ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}



