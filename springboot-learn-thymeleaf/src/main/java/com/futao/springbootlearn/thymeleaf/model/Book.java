package com.futao.springbootlearn.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String id;
    private String author;
    private String name;
}
