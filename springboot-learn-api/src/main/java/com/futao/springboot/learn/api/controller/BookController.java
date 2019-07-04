package com.futao.springboot.learn.api.controller;

import com.futao.springboot.learn.api.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping
    public Book book() {
        return new Book("1", "futao", "当幸福来敲门", new Date());
    }

}
