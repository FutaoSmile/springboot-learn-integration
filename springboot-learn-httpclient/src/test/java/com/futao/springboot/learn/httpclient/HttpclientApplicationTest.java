package com.futao.springboot.learn.httpclient;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author futao
 * Created on 2019-08-01.
 */
public class HttpclientApplicationTest {
    @Test
    public void test4() {
        A a = JSON.parseObject("{\"_A_A_A_\":\"123\"}", A.class);
        System.out.println(a.aaa);
    }

    @Getter
    @Setter
    static class A {
        private String aaa;
    }

    @Test
    public void test3() {
        Flux<String> stringFlux = WebClient.create()
                .post()
                .uri("http://localhost:8888/user/sendRegisterVerifyCode")
                .retrieve()
                .bodyToFlux(String.class);
        stringFlux.subscribe(System.out::println);
    }

    @Test
    public void test2() {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForEntity("http://localhost:8888/user/sendRegisterVerifyCode", )
    }

    @Test
    public void test1() {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("mobile", "18797811992");
        restTemplate.execute("http://localhost:8888/user/sendRegisterVerifyCode", HttpMethod.POST, (request) -> {
        }, new ResponseExtractor<Object>() {
            @Override
            public Object extractData(ClientHttpResponse response) throws IOException {
                return null;
            }
        }, uriVariables);
    }
}