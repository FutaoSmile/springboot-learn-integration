package com.futao.springboot.learn.mybatisplus.transactiont.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.futao.springboot.learn.mybatisplus.entity.IdTimeEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author futao
 * @date 2020/4/14.
 */
@Getter
@Setter
@TableName("a")
public class A extends IdTimeEntity<A> {

    @TableField("a")
    private String a;
}
