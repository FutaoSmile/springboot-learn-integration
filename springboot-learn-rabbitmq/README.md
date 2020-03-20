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
* Consumer消费者限流
    * 设置为手动签收， 设置prefetch: n。消息ACK之前，rabbitMQ不会继续向消费者发送消息。从而达到限流的目的
    
    
    


* 不错的文章: https://www.cnblogs.com/mfrank/p/11184929.html
