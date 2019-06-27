package com.futao.springboot.learn.api.config;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN);
        converters.add(fastJsonHttpMessageConverter);
    }
}
