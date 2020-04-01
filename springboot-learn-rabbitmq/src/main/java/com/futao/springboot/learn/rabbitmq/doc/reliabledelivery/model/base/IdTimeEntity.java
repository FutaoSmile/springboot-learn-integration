package com.futao.springboot.learn.rabbitmq.doc.reliabledelivery.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author futao
 * @date 2020/3/31.
 */
@Getter
@Setter
public class IdTimeEntity extends Model<IdTimeEntity> implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 创建时间
     */
    @TableField(value = "create_date_time", fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_date_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateDateTime;
}
