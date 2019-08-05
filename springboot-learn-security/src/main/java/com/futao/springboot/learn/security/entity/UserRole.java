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
@Entity //说明这个class是实体类，并且使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名
@Table(name = "security_user_role")
//如果想改变这种默认的orm规则，就要使用@Table来改变class名与数据库中表名的映射规则，@Column来改变class中字段名与db中表的字段名的映射规则
public class UserRole {

    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
