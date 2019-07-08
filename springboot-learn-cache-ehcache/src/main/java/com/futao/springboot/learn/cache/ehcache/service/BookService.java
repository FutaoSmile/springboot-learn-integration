package com.futao.springboot.learn.cache.ehcache.service;

import com.futao.springboot.learn.cache.ehcache.config.CacheKeyGenerator;
import com.futao.springboot.learn.cache.ehcache.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@Service
@Slf4j
@CacheConfig(cacheNames = {"book_cache"})
public class BookService {

    @Resource
    private CacheKeyGenerator cacheKeyGenerator;

    private static List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book(1, "钢铁是怎么炼成的", "佚名"));
        bookList.add(new Book(2, "围城", "佚名"));
        bookList.add(new Book(3, "我与地坛", "史铁生"));
    }

    /**
     * Cacheable 检查缓存中是否存在，存在即返回，不存在则插入缓存
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#id")//keyGenerator = "cacheKeyGenerator"
    public Book getBookById(Integer id) {
        log.info("getBookById run...");
        Optional<Book> book = bookList.stream().filter(it -> it.getId().equals(id)).findFirst();
        return book.orElse(null);
    }

    /**
     * CachePut每次都直接往缓存中插
     *
     * @param book
     * @return
     */
    @CachePut(key = "#book.id")
    public Book update(Book book) {
        log.info("update run...");
        Book bookById = bookList.stream().filter(it -> it.getId().equals(book.getId())).findFirst().get();
        bookList.remove(bookById);
        bookById.setAuthor(book.getAuthor());
        bookById.setName(book.getName());
        bookList.add(bookById);
        return bookById;
    }

    /**
     * CacheEvict删除缓存
     *
     * @param id
     */
    @CacheEvict(key = "#id", beforeInvocation = true)//keyGenerator = "cacheKeyGenerator"
    public void delete(Integer id) {
        log.info("删除缓存,{}", id);
    }
}
