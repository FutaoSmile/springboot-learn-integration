package com.futao.springboot.learn.enhance.config;

import com.futao.springboot.learn.enhance.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/interceptorExclude");
    }
}
