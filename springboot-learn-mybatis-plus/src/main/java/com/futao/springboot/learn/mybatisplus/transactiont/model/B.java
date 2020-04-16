package com.futao.springboot.learn.mybatisplus.transactiont.model;

import com.futao.springboot.learn.mybatisplus.entity.IdTimeEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author futao
 * @date 2020/4/14.
 */
@Getter
@Setter
public class B extends IdTimeEntity<B> {

    private String b;
}
