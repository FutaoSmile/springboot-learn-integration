package com.futao.springboot.learn.rabbitmq.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static <T> T getBean(Class<T> aClass) {
        return context.getBean(aClass);
    }

    public static <T> T getBean(String name, Class<T> aClass) {
        return context.getBean(name, aClass);
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }
}
