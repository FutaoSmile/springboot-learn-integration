package com.futao.springboot.learn.jpa.service;

import com.futao.springboot.learn.jpa.dao.BookDao;
import com.futao.springboot.learn.jpa.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Service
public class BookService {
    @Resource
    private BookDao bookDao;

    public Book add(Book book) {
        return bookDao.save(book);
    }

    public Book update(Book book) {
        return bookDao.save(book);
    }

    public void delete(int id) {
        bookDao.deleteById(id);
    }

    public Optional<Book> byId(int id) {
        return bookDao.findById(id);
    }

    public List<Book> list() {
//        PageRequest pageRequest = PageRequest.of(1, 20);
        return bookDao.findAll();
    }

    public List<Book> getBooksByNameStartingWith(String name) {
        return bookDao.getBooksByNameStartingWith(name);
    }

    public List<Book> getBooksByPriceGreaterThan(float price) {
        return bookDao.getBooksByPriceGreaterThan(price);
    }

    public List<Book> getBookByIdAndAuthor(int id, String author) {
        return bookDao.getBookByIdAndAuthor(id, author);
    }
}
