package com.futao.springboot.learn.redis.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author futao
 * Created on 2019-07-05.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book implements Serializable {
    private Integer id;
    private String name;
    private String author;
}
