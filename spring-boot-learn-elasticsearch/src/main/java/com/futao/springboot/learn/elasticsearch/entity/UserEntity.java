package com.futao.springboot.learn.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author ft
 * @date 2021/1/14
 */
@Document(indexName = UserEntity.ES_INDEX_NAME)
public class UserEntity {
    public static final String ES_INDEX_NAME = "es_user";

}
