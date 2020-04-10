package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.rabbitmq;

import lombok.extern.slf4j.Slf4j;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Slf4j
//@Component
public class Consumer {

//    @Resource
//    private MessageMapper messageMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @RabbitListener(queues = "${app.rabbitmq.queue.user}")
//    public void userConsumer(String body, Message message, Channel channel) throws IOException, InterruptedException {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        channel.basicAck(deliveryTag, true);
//        log.info("{}执行业务...", StringUtils.repeat(">", 30));
//        Thread.sleep(200L);
//    }
}
