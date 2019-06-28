package com.futao.springboot.learn.enhance.controller;

import com.futao.springboot.learn.enhance.service.AopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@RestController
public class AopController {

    @Resource
    private AopService aopService;
    private static final String noReturnValue = "noReturnValue";

    private static final String returningValue = "returningValue";

    private static final String exception = "exception";


    @GetMapping("/aop")
    public String aop(@RequestParam String parameter) {
        switch (parameter) {
            case noReturnValue: {
                aopService.noReturnValue();
                break;
            }
            case returningValue: {
                return aopService.returningValue(parameter);
            }
            case exception: {
                aopService.exception(parameter);
                break;
            }
            default: {
            }
        }
        return "success";
    }
}
