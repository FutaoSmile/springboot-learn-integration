package com.futao.springboot.learn.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author futao
 * Created on 2019-07-23.
 */
@Getter
@Setter
@Table(name = "security_role")
@Entity
public class Role {

    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_ch")
    private String nameCh;
}
