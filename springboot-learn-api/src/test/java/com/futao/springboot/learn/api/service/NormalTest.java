package com.futao.springboot.learn.api.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author futao
 * <p>
 * Created on 2019-07-09.
 */
public class NormalTest {
    @Getter
    @Setter
    class FeatureOptionInput {

        private String featureId;

        /**
         * 可能是个集合，可能可以多选
         */
        private List<String> optionIdList;
    }

    @Test
    public void test8() {
        List<FeatureOptionInput> featureOptionInputList = new ArrayList<>();
        FeatureOptionInput featureOptionInput = new FeatureOptionInput();
        featureOptionInput.setFeatureId("1");
        ArrayList<String> optionIdList = new ArrayList<>();
        optionIdList.add("o1");
        optionIdList.add("o2");
        optionIdList.add("o3");
        featureOptionInput.setOptionIdList(optionIdList);


        featureOptionInputList.add(featureOptionInput);
        featureOptionInputList.add(featureOptionInput);
        List<String> userOptionIdList = featureOptionInputList.stream().flatMap(it -> it.getOptionIdList().stream()).collect(Collectors.toList());
        System.out.println(userOptionIdList);

    }

    public static boolean isContainsEachOther(Collection a, Collection b) {
        return a.containsAll(b) && b.containsAll(a);
    }

    @Test
    public void test7() {
        String[] split = "ABD AND DDD NOT WHL{WHL1}".split(" AND| OR|NOT");
        System.out.println(split);
    }

    @Test
    public void test6() {
        String s = "{AAA{AAA} AND BBB OR CCC NOT DDD}".replaceAll("[[^AND]|[^OR+]|[^NOT+]]", "$");
        String s2 = "{AAA{AAA} AND BBB OR CCC NOT DDD}".replaceAll("[^(AND)|(OR)|(NOT)]", "");
        String s3 = "{AAA{AAA} AND BBB OR CCC NOT DDD}".replaceAll("[(AND)|(OR)|(NOT)]", "");
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
    }

    @Test
    public void test5() {
        String[] featureOption = "PROF{MID,HIGH} AND DOOR{DR01}".split(" AND| OR|NOT");
        for (String s : featureOption) {
            String[] featureList = s.split("[{].*[}]");
            for (String feature : featureList) {

            }
        }
        System.out.println(Arrays.toString(featureOption));
    }

    @Test
    public void test3() {

        List<String> mailList = new ArrayList<>();
        mailList.add("123@qq.com");
        mailList.add("123@qq.om");
        mailList.add("12312@qasdas");
        mailList.add("123121.com");
        mailList.add("@qweqw.com");
        mailList.add("@qweqw.cn");
        List<String> realMailList = mailList
                .stream()
                .filter(it -> StringUtils.isNotBlank(it) && it.contains("@") && it.contains("."))
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(realMailList));
    }

    @Test
    public void test2() {
        Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+");
        Pattern.compile("^(.)+@(.)+(.[a-zA-Z0-9_-])+");

    }

    @Test
    public void test1() {

        List<String> emailList = Splitter
                .on(";")
                .trimResults()
                .splitToList(JSON.parseObject("{\"mail\":{\"refund\":\"\",\"remaining\":\"taof@wicrenet.com;shaoxin5345@icloud.com\",\"sapExport\":\"taof@wicrenet.com;taof@wicrenet.com\"}}")
                        .getJSONObject("mail")
                        .getString("sapExport"));

        System.out.println(emailList);
    }
}