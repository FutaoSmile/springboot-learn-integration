package com.futao.springboot.learn.api.service;

import org.junit.Test;

/**
 * @author futao
 * <p>
 * Created on 2019-07-09.
 */
public class NormalTest {

    @Test
    public void test2() {
        System.out.println("soijsalk(akjdss()adsa()asdas".indexOf("("));
        System.out.println("soijsalk(akjdss()adsa()asdas".indexOf('('));
    }

    @Test
    public void test1() {
        String[] split = "".split("\\(");
    }
}