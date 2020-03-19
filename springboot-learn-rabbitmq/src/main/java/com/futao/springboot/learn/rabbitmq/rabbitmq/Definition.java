package com.futao.springboot.learn.rabbitmq.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Exchange与Queue绑定
 *
 * @author futao
 * @date 2020/3/14.
 */
@Component
public class Definition {


    @Bean("userExchange")
    public FanoutExchange userFanoutExchange() {
        return new FanoutExchange("user-exchange", true, false);
    }

    @Bean("userQueue")
    public Queue userQueue() {
        return new Queue("user-queue", true);
    }

    @Bean("userQEBind")
    public Binding userBind(
            @Qualifier("userQueue")
                    Queue userQueue,
            @Qualifier("userExchange")
                    FanoutExchange userFanoutExchange
    ) {
        return BindingBuilder.bind(userQueue)
                .to(userFanoutExchange);
    }


}
