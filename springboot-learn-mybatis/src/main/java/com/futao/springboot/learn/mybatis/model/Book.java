package com.futao.springboot.learn.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    private String name;
    private String author;
    private float amount;
}