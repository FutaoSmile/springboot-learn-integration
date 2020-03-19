package com.futao.springboot.learn.rabbitmq.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/3/19.
 */
@Getter
@Setter
@Builder
public class Order {

    @Tolerate
    public Order() {
    }

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String orderNum;

    private String address;

    @TableField(value = "create_date_time", fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @TableField(value = "update_date_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateDateTime;

}
