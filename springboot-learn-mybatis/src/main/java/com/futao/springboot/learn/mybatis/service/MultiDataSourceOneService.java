package com.futao.springboot.learn.mybatis.service;

import com.futao.springboot.learn.mybatis.mapper.one.MultiDataSourceOneMapper;
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
public class MultiDataSourceOneService {

    @Autowired
    private MultiDataSourceOneMapper mapperOne;

    @Transactional(rollbackFor = Exception.class)
    public void add(String moreInfo) {
        MultiDataSourceEntity entity = new MultiDataSourceEntity();
        long currentTimeMillis = System.currentTimeMillis();
        entity.setMoreInfo(moreInfo);
        entity.setCreateDate(new Timestamp(currentTimeMillis));
        entity.setUpdateDate(new Timestamp(currentTimeMillis));
        mapperOne.add(entity);
    }
}
