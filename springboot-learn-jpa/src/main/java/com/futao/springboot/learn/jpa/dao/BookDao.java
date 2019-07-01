package com.futao.springboot.learn.jpa.dao;

import com.futao.springboot.learn.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author futao
 * Created on 2019-07-01.
 */
public interface BookDao extends JpaRepository<Book, Integer> {

    List<Book> getBooksByNameStartingWith(String name);

    List<Book> getBooksByPriceGreaterThan(Float price);

    @Query("select b from jpa_book b where id>:id and author=:author")
    List<Book> getBookByIdAndAuthor(@Param("id") int id, @Param("author") String author);

}
