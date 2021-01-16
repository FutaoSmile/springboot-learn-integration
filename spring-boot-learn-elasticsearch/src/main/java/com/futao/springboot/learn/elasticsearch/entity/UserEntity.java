package com.futao.springboot.learn.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPoint;

/**
 * @author ft
 * @date 2021/1/14
 */
@Getter
@Setter
@Document(indexName = UserEntity.ES_INDEX_NAME)
public class UserEntity extends IdTimeEntity {
    public static final String ES_INDEX_NAME = "es_user";
    private String username;
    private Integer age;
    private String mobile;
    private GeoJsonPoint address;
}
