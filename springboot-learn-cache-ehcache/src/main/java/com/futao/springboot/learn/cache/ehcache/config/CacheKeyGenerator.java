package com.futao.springboot.learn.cache.ehcache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 自定义cacheKey的生成规则
 *
 * @author futao
 * Created on 2019-07-08.
 */
@Component
public class CacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + ":" + method.getName() + ":" + Arrays.toString(params);
    }
}
