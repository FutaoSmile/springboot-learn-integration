package com.futao.springboot.learn.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author ft
 * @date 2021/1/14
 */
@Getter
@Setter
@Document(indexName = TagEntity.ES_INDEX_NAME)
public class TagEntity extends IdTimeEntity {
    public static final String ES_INDEX_NAME = "es_tag";

    private String name;
    private Integer type;
}
