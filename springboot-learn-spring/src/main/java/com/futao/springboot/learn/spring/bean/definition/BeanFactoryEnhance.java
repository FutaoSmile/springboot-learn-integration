package com.futao.springboot.learn.spring.bean.definition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Component
public class BeanFactoryEnhance implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("userService");
        System.out.println(userBeanDefinition.isSingleton());
        System.out.println(userBeanDefinition.getBeanClassName());
    }
}
