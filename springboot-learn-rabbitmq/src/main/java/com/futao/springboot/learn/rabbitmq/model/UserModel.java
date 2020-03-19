package com.futao.springboot.learn.rabbitmq.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/3/14.
 */
@Getter
@Setter
@Builder
@TableName("user")
public class UserModel extends Model<UserModel> {

    @Tolerate
    public UserModel() {
    }

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("age")
    private int age;

    @TableField("birthday")
    private LocalDate birthday;

    @TableField(value = "create_date_time", fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @TableField(value = "update_date_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateDateTime;
}
