package com.futao.springboot.learn.session.share.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author futao
 * Created on 2019-07-05.
 */
@RestController
public class SessionController {

    @GetMapping("/save")
    public void save(HttpSession httpSession, @RequestParam String name, @RequestParam String value) {
        httpSession.setAttribute(name, value);
    }

    @GetMapping("/get/{name}")
    public Object get(HttpSession httpSession, @PathVariable String name) {
        return httpSession.getAttribute(name);
    }
}
