package com.futao.springboot.learn.persistent.jdbc.tamplate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplate多数据源使用方法：
 * 1.在配置文件中配置两个数据源
 * 2.创建两个DataSource对象
 * 3.创建两个JdbcTemplate，分别注入两个数据源
 * 4.根据需求使用某个jdbcTemplate
 *
 * @author futao
 * Created on 2019-06-28.
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


    @Bean("jdbcTemplateOne")
    public JdbcTemplate jdbcTemplateOne(@Qualifier("dataSourceOne") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbcTemplateTwo")
    public JdbcTemplate jdbcTemplateTwo(@Qualifier("dataSourceTwo") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
