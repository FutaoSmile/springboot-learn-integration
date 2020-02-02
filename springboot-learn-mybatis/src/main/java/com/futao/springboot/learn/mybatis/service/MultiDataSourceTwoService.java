package com.futao.springboot.learn.mybatis.service;

import com.futao.springboot.learn.mybatis.mapper.two.MultiDataSourceTwoMapper;
import com.futao.springboot.learn.mybatis.model.MultiDataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author futao
 * @date 2020/2/2.
 */
@Service
public class MultiDataSourceTwoService {

    @Autowired
    private MultiDataSourceTwoMapper mapperTwo;

    @Transactional(rollbackFor = Exception.class, transactionManager = "platformTransactionManagerTwo")
    public void add(String moreInfo, int num) {
        System.out.println(1 / num);
        MultiDataSourceEntity entity = new MultiDataSourceEntity();
        long currentTimeMillis = System.currentTimeMillis();
        entity.setMoreInfo(moreInfo);
        entity.setCreateDate(new Timestamp(currentTimeMillis));
        entity.setUpdateDate(new Timestamp(currentTimeMillis));
        mapperTwo.add(entity);
    }
}
