package com.futao.springboot.learn.file.upload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")
//                .setCacheControl(CacheControl.noCache());


//        registry.addResourceHandler("/singleFileUpload/**")
//                .addResourceLocations("classpath:/singleFileUpload/")
//                .setCacheControl(CacheControl.maxAge(10, TimeUnit.DAYS));
    }
}
