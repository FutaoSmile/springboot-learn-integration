package com.futao.springboot.learn.security.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author futao
 * Created on 2019-07-23.
 */
public class MyWebSecurityConfigTest {

    @Test
    public void test1() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("nobug666"));

    }
}