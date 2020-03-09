package com.futao.springboot.learn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/3/9.
 */
@Getter
@Setter
public class IdTimeEntity {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "create_date_time", fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(value = "update_date_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateDateTime;
}
