package com.futao.springboot.learn.freemarker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 不需要controller转发的路径可以直接配置在这里
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login")
                .setViewName("/login");
    }
}
