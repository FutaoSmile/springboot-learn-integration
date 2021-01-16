package com.futao.springboot.learn.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ft
 * @date 2021/1/15
 */
@Getter
@Setter
public class UserTagEntity extends IdEntity {
    private Integer userId;
    private Integer tagId;
}
