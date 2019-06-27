package com.futao.springboot.learn.freemarker.controller;

import com.futao.springboot.learn.freemarker.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @author futao
 * Created on 2019-06-27.
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @GetMapping("/books")
    public ModelAndView books() {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("1", "futao", "帅哥养成记"));
        bookList.add(new Book("2", "noName", "I do not care"));
        return new ModelAndView("books", "bookList", bookList);
    }
}