package com.futao.springboot.learn.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "jpa_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "c_name", length = 255)
    private String name;

    @Column(name = "c_author", length = 255)
    private String author;

    @Column(name = "c_price")
    private float price;

    @Transient
    private String desc;
}
