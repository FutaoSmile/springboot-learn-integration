package com.futao.springboot.learn.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Springboot与es整合
 * 官方文档: https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#reference
 * 官方demo: https://github.com/spring-projects/spring-data-examples/tree/master/elasticsearch
 * <p>
 * <p>
 * IndexOperations defines actions on index level like creating or deleting an index.
 * DocumentOperations defines actions to store, update and retrieve entities based on their id.
 * SearchOperations define the actions to search for multiple entities using queries
 * ElasticsearchOperations combines the DocumentOperations and SearchOperations interfaces.
 * **********
 * Queries
 * * CriteriaQuery      封装好的查询方法
 * * StringQuery        封装json查询条件
 * * NativeSearchQuery  用来查询CriteriaQuery无法实现的复杂查询，如需要使用到聚合函数
 *
 * @author ft
 * @date 2021/1/14
 */
@EnableElasticsearchRepositories(basePackages = "com.futao.springboot.learn.elasticsearch.dao")
@SpringBootApplication
public class ElasticSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
