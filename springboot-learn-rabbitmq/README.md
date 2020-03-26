* 通过代码定义Exchange，Queue，Binding
* 消息的可靠性投递，开启publish-confirm
* publish-return 消息路由失败监听
* DeadLetter 死信队列-死信消息处理    
    * 有两种场景下消息会被发送到死信队列
        1. 自动签收场景下：消息处理失败，并且重试次数用尽
        2. 手动签收场景下：消息basicNac()，并且requeue为false
       
       (手动签收模式下，消息重试次数没有用。因为basicNack()时如果requeue=true，消息会一直重发到队列首尾，并且一直无限循环。requeue=false时，消息会直接发送到死信队列)
        
    * 文档写的场景：
        1. 消息被否定确认，使用 channel.basicNack 或 channel.basicReject ，并且此时requeue 属性被设置为false。
        2. 消息在队列的存活时间超过设置的TTL时间。
        3. 消息队列的消息数量已经超过最大队列长度。
* TTL 消息过期
    * 如果TTL设置在队列queue上，那么每条发送到该queue的消息都会在指定的TTL时间内过期。
    * 如果TTL设置在消息上，rabbitMQ只会检查第一个消息是否过期，那么第二个消息虽然已经过期了，也无法被检测到，所以会导致业务不正常
        * 处理的方法：
      TODO 还没有搞清楚，消息过期和延迟
* 延迟队列：（[可参考](https://a601942905git.github.io/2019/01/28/rabbitmq/spring-boot-rabbitmq-xi-lie-zhi-liu-shi-xian-xiao-xi-yan-chi/spring-boot-rabbitmq-xi-lie-zhi-liu-shi-xian-xiao-xi-yan-chi/)）
    * 需要安装插件
    * 比如订单30分钟内没有被支付则自动取消：
        * 消息发送到MQ，设置延迟时间为30分钟，则消息会在30分钟到再发送到消费者。
        * 备注：可以发现这类消息在发送到RabbitMQ之后会触发NO_ROUTE回调，猜测是消息会在exchange保留30分钟之后，再路由到对应的Queue
* Consumer消费者限流
    * 设置为手动签收， 设置prefetch: n。消息ACK之前，rabbitMQ不会继续向消费者发送消息。从而达到限流的目的
    
    
    
* 如果想直接发送Model和接收Model，
例如 
```java
    @RabbitHandler
    @RabbitListener(queues = "ttl-queue")
    public void userReceiver(UserModel body, Channel channel, @Headers Map<String, Object> headers, Message message) throws IOException, InterruptedException {
        log.info("user开始消费[{}]", JSON.toJSONString(body));
}
```
则将Model实现`implements Serializable`接口即可


* 不错的文章: https://www.cnblogs.com/mfrank/p/11184929.html
