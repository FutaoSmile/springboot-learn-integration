package com.futao.springboot.learn.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Configuration
@MapperScan(basePackages = "com.futao.springboot.learn.mybatis.mapper", sqlSessionFactoryRef = "sqlSessionFactoryOne")
public class MybatisConfigOne {

    @Bean("sqlSessionFactoryOne")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplateOne")
    public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
