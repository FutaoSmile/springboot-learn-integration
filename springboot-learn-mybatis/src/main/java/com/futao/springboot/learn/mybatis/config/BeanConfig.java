package com.futao.springboot.learn.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Configuration
public class BeanConfig {

    @Bean("dataSourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DruidDataSource druidDataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DruidDataSource druidDataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }
}
