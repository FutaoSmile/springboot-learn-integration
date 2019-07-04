package com.futao.springboot.learn.api.controller;

import com.futao.springboot.learn.api.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-07-04.
 */
@RestController
public class AsyncController {


    @Resource
    private AsyncService asyncService;

    @GetMapping("/sync")
    public long sync() {
        long startTime = System.currentTimeMillis();
        asyncService.sync();
        return System.currentTimeMillis() - startTime;
    }

    @GetMapping("/async")
    public long async() {
        long startTime = System.currentTimeMillis();
        asyncService.async();
        return System.currentTimeMillis() - startTime;
    }

}
