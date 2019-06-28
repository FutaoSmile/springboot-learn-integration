package com.futao.learn.persistent.jdbc.tamplate.controller;

import com.futao.learn.persistent.jdbc.tamplate.model.Book;
import com.futao.learn.persistent.jdbc.tamplate.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    public String add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @PutMapping
    public String update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return bookService.delete(id);
    }

    @GetMapping("/{id}")
    public Book byId(@PathVariable int id) {
        return bookService.byId(id);
    }

    @GetMapping
    public List<Book> list(@RequestParam String author) {
        return bookService.list(author);
    }
}
