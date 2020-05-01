package com.futao.springboot.learn.event.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/5/1
 */
@Getter
@Setter
@Builder
public class User {

    @Tolerate
    public User() {
    }

    private String id;

    private String fullName;

    private LocalDate birthday;
}

