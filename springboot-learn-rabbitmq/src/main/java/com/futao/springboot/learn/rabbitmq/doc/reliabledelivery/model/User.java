package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.base.IdTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Getter
@Setter
@Builder
public class User extends IdTimeEntity implements Serializable {

    @Tolerate
    public User() {
    }

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

}
