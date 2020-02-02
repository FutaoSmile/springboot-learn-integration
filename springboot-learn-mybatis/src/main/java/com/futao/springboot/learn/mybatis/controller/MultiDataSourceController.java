package com.futao.springboot.learn.mybatis.controller;

import com.futao.springboot.learn.mybatis.service.MultiDataSourceOneService;
import com.futao.springboot.learn.mybatis.service.MultiDataSourceTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/addOne")
    public void addOne(@RequestParam String moreInfo, @RequestParam int num) {
        oneService.add(moreInfo, num);
    }

    @GetMapping("/addTwo")
    public void addTwo(@RequestParam String moreInfo, @RequestParam int num) {
        twoService.add(moreInfo, num);
    }
}
