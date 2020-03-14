package com.futao.springboot.learn.rabbitmq.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/3/14.
 */
@Getter
@Setter
@Builder
public class UserModel {

    @Tolerate
    public UserModel() {
    }

    private int id;
    private String userName;
    private int age;
    private LocalDate birthday;
}
