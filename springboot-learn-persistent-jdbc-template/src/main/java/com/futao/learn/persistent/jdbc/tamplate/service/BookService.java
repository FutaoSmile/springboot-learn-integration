package com.futao.learn.persistent.jdbc.tamplate.service;

import com.futao.learn.persistent.jdbc.tamplate.dao.BookDao;
import com.futao.learn.persistent.jdbc.tamplate.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author futao
 * Created on 2019-06-28.
 */
@Service
public class BookService {
    @Resource
    private BookDao bookDao;

    public String add(Book book) {
        return bookDao.add(book) > 0 ? "新增成功" : "新增失败";
    }

    public String update(Book book) {
        return bookDao.update(book) > 0 ? "修改成功" : "修改失败";
    }

    public String delete(int id) {
        return bookDao.delete(id) > 0 ? "新增删除功" : "删除失败";
    }

    public Book byId(int id) {
        return bookDao.byId(id);
    }

    public List<Book> list(String author) {
        return bookDao.list(author);
    }
}
