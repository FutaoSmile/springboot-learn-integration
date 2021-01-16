package com.futao.springboot.learn.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author ft
 * @date 2021/1/15
 */
@Getter
@Setter
public class IdEntity {
    @Id
    private Integer id;
}
