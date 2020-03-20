* 通过代码定义Exchange，Queue，Binding
* 消息的可靠性投递，开启publish-confirm
* publish-return 消息路由失败监听
* DeadLetter 死信队列-死信消息处理
* TTL 消息过期
* Consumer消费者限流
    * 设置为手动签收， 设置prefetch: n。消息ACK之前，rabbitMQ不会继续向消费者发送消息。从而达到限流的目的