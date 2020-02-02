package com.futao.springboot.learn.mybatis.controller;

import com.futao.springboot.learn.mybatis.service.MultiDataSourceOneService;
import com.futao.springboot.learn.mybatis.service.MultiDataSourceTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * @date 2020/2/2.
 */
@RestController
@RequestMapping("/multiDataSource")
public class MultiDataSourceController {

    @Autowired
    private MultiDataSourceOneService oneService;

    @Autowired
    private MultiDataSourceTwoService twoService;

    @GetMapping("/addOne/{moreInfo}")
    public void addOne(@PathVariable String moreInfo) {
        oneService.add(moreInfo);
    }

    @GetMapping("/addTwo/{moreInfo}")
    public void addTwo(@PathVariable String moreInfo) {
        twoService.add(moreInfo);
    }
}
