package com.futao.springboot.learn.enhance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Slf4j
@Service
public class AopService {

    public void noReturnValue() {
        log.info("noReturnValue run");
    }

    public String returningValue(String parameter) {
        parameter = "niubi";
        return parameter;
    }

    public String exception(String parameter) {
        int a = 1 / 0;
        return parameter;
    }
}
