package com.futao.springboot.learn.persistent.jdbc.tamplate.dao;

import com.futao.springboot.learn.persistent.jdbc.tamplate.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * update/batchUpdate   -   增删改
 * query/queryForObject/queryForList    -   查询
 * execute  -   执行任意sql语句
 *
 * @author futao
 * Created on 2019-06-28.
 */
@Repository
public class BookDao {

    @Qualifier("jdbcTemplateOne")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Book book) {
        return jdbcTemplate.update(
                "insert into springboot_learn_persistent_jdbc_template.book(name, author) values (?,?)",
                book.getName(),
                book.getAuthor());
    }

    public int update(Book book) {
        return jdbcTemplate.update("update springboot_learn_persistent_jdbc_template.book set name=?,author=?where id=?",
                book.getName(),
                book.getAuthor(),
                book.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("delete from springboot_learn_persistent_jdbc_template.book where id=?", id);
    }

    public Book byId(int id) {
        return jdbcTemplate.queryForObject("select * from springboot_learn_persistent_jdbc_template.book where id=?", new Object[]{id}, Book.class);
    }

    public List<Book> list(String author) {
        String s = "select * from springboot_learn_persistent_jdbc_template.book";
        if (StringUtils.isNotEmpty(author)) {
            s += " where author like " + "'%" + author + "%'";
        }
        //使用这种方式结果只能是简单类型，如String，int等
//        List<Book> bookList = jdbcTemplate.queryForList(s, Book.class);
        List<Book> bookList = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }

}
