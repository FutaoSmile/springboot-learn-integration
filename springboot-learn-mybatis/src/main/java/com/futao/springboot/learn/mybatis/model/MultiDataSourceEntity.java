package com.futao.springboot.learn.mybatis.model;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author futao
 * @date 2020/2/2.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultiDataSourceEntity {

    private String id = RandomUtil.simpleUUID();

    private String moreInfo;

    private Timestamp createDate;

    private Timestamp updateDate;
}
