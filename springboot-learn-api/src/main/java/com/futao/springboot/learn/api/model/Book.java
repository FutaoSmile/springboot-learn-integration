package com.futao.springboot.learn.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {
    private String id;
    private String author;
    @JsonAlias("goodName")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date publishDate;
}
