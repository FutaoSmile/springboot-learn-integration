package com.futao.springboot.learn.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.util.Date;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
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
    private Money price;

    @Transient
    private String desc;

    @Column(name = "create_date_time")
    @CreationTimestamp
    private Date createDateTime;

    @Column(name = "update_date_time")
    @UpdateTimestamp
    private Date updateDateTime;
}
