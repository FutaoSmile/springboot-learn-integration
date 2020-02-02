package com.futao.springboot.learn.mybatis.service;

import com.futao.springboot.learn.mybatis.mapper.one.BookMapper;
import com.futao.springboot.learn.mybatis.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author futao
 * Created on 2019-07-01.
 */
@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    public String add(Book book) {
        return bookMapper.add(book) > 0 ? "新增成功" : "新增失败";
    }

    public String update(Book book) {
        return bookMapper.update(book) > 0 ? "修改成功" : "修改失败";
    }

    public String delete(int id) {
        return bookMapper.delete(id) > 0 ? "删除成功" : "删除失败";
    }

    public Book byId(int id) {
        return bookMapper.byId(id);
    }

    public List<Book> list() {
        return bookMapper.list();
    }
}