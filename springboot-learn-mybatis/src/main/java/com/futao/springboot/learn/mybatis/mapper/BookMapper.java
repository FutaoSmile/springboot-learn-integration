package com.futao.springboot.learn.mybatis.mapper;

import com.futao.springboot.learn.mybatis.model.Book;

import java.util.List;

/**
 * @author futao
 * Created on 2019-07-01.
 */
//@Mapper
public interface BookMapper {

    int add(Book book);

    int update(Book book);

    int delete(int id);

    Book byId(int id);

    List<Book> list();
}
