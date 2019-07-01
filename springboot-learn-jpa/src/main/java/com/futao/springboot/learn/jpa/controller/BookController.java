package com.futao.springboot.learn.jpa.controller;

import com.futao.springboot.learn.jpa.model.Book;
import com.futao.springboot.learn.jpa.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    public Book add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    public Book byId(@PathVariable int id) {
        Optional<Book> book = bookService.byId(id);
        return book.orElse(null);
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("/getBookByIdAndAuthor")
    public List<Book> getBookByIdAndAuthor(@RequestParam int id, @RequestParam String author) {
        return bookService.getBookByIdAndAuthor(id, author);
    }

    @GetMapping("/getBooksByNameStartingWith")
    public List<Book> getBooksByNameStartingWith(@RequestParam String name) {
        return bookService.getBooksByNameStartingWith(name);
    }


    @GetMapping("/getBooksByPriceGreaterThan")
    public List<Book> getBooksByPriceGreaterThan(@RequestParam float price) {
        return bookService.getBooksByPriceGreaterThan(price);
    }
}
