package com.futao.learn.persistent.jdbc.tamplate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String author;
}
