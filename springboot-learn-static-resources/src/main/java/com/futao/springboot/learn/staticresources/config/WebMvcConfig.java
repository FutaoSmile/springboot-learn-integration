package com.futao.springboot.learn.staticresources.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 静态资源路径银蛇
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .setOrder(1)
                .addResourceHandler("/testSourcePath/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache());

        registry
                .setOrder(2)
                .addResourceHandler("/forTestSourcePath/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache());
    }
}
