package com.futao.springboot.learn.es.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author futao
 * @date 2020/3/30.
 */
@Getter
@Setter
@Builder
@Document(indexName = "", type = "user")
public class User {

    @Tolerate
    public User() {
    }

    @Field(type = FieldType.Text, index = true, store = true)
    private String id;

    @Field(type = FieldType.Text, index = true, store = true)
    private String fullName;

    @Field(type = FieldType.Integer, index = true, store = true)
    private int age;

    @Field(type = FieldType.Text, index = true, store = true)
    private String tagId;

    @Field(type = FieldType.Text, index = true, store = true)
    private String tagName;
}
