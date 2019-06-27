package com.futao.springboot.learn.api.config;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;
import java.nio.charset.Charset;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@Configuration
public class ApiConfig {
    /**
     * GsonHttpMessageConverter
     * 使用自定义的消息转换器代替默认的jacksonHttpMessageConverter
     *
     * @return
     */
//    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(DatePattern.CHINESE_DATE_PATTERN);
        builder.excludeFieldsWithModifiers(Modifier.PROTECTED);
        Gson gson = builder.create();
        converter.setGson(gson);
        return converter;
    }

    /**
     * 对于HttpMessageConverter，除了这种配置一个Bean的方式，还可以在MvcConfig中配置#extendMessageConverters
     * TODO 为什么不需要在MvcConfig中配置#extendMessageConverters，是通过BeanPostProcessor加载的么
     * FastJsonHttpMessageConverter
     * 使用自定义的消息转换器代替默认的jacksonHttpMessageConverter
     *
     * @return
     */
//    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat(DatePattern.CHINESE_DATE_PATTERN);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
                , SerializerFeature.WriteNullNumberAsZero
                , SerializerFeature.WriteClassName
                , SerializerFeature.WriteMapNullValue
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }
}
