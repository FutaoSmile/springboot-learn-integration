package com.futao.springboot.learn.cache.ehcache.controller;

import com.futao.springboot.learn.cache.ehcache.model.Book;
import com.futao.springboot.learn.cache.ehcache.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@RequestMapping("/book")
@RestController
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/getBookById")
    public Book getBookById(@RequestParam Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/update")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        bookService.delete(id);
    }
}
