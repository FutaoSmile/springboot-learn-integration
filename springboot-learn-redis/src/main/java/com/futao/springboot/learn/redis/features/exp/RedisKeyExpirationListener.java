package com.futao.springboot.learn.redis.features.exp;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听key的过期行为
 *
 * @author futao
 * @date 2020/4/16.
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("------------------------------------------------------------");
        System.out.println(new String(message.getBody()));
        System.out.println(new String(pattern));
        System.out.println(message.getChannel());
    }
}
