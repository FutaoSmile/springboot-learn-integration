package com.futao.springboot.learn.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDateTime;

/**
 * @author ft
 * @date 2021/1/15
 */
@Getter
@Setter
public class IdTimeEntity extends IdEntity {
    private Integer createBy;
    @Field(format = DateFormat.basic_date_time)
    private LocalDateTime createDateTime;
    private Integer updateBy;
    /**
     * 使用uuuu代替yyyy
     * https://www.elastic.co/guide/en/elasticsearch/reference/current/migrate-to-java-time.html#java-time-migration-incompatible-date-formats
     */
    @Field(pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updateDateTime;
}
