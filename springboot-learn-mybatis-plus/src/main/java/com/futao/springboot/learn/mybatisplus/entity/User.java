package com.futao.springboot.learn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/3/9.
 */
@Builder
@Getter
@Setter
@TableName("user")
public class User extends IdTimeEntity<User> {

    @Tolerate
    public User() {
    }

    @TableField(value = "full_name")
    private String fullName;

    @TableField(value = "age")
    private int age;

    @TableField(value = "birthday")
    private LocalDate birthday;
}
