package com.futao.springboot.learn.mybatisplus.transactiont.service;

import com.futao.springboot.learn.mybatisplus.transactiont.model.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author futao
 * @date 2020/4/14.
 */
@Service
public class AService {

    @Autowired
    private AService aService;

    @Transactional(rollbackFor = Exception.class)
    public void m1() {
        A a = new A();
        a.setA("m1");
        a.insert();

        aService.m2();
        aService.m3();
    }


    private void m2() {
        A a = new A();
        a.setA("m2");
        a.insert();
    }

    private void m3() {
        A a = new A();
        a.setA("m3");
        a.insert();
        throw new RuntimeException("----");
    }
}
