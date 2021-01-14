package com.futao.springboot.learn.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Parent;

/**
 * @author ft
 * @date 2021/1/14
 */
@Document(indexName = Tag.ES_INDEX_NAME)
public class Tag {
    public static final String ES_INDEX_NAME = "es_tag";

    @Parent()
    private String userId;
}
