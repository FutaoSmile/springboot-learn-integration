package com.futao.springboot.learn.rest.dao;

import com.futao.springboot.learn.rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * 默认是实体名加s即是请求路径，路径符合REST风格
 *
 * @author futao
 * Created on 2019-07-08.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 64000L)
@RepositoryRestResource(
        exported = true,//是否暴露
        path = "book",
        collectionResourceRel = "bookList",
        collectionResourceDescription = @Description("书籍列表"),
        itemResourceRel = "SingleBook",
        itemResourceDescription = @Description("单本书籍"))
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthorLike(@Param("author") String author);

    @RestResource(path = "bookResource", rel = "bookResource")
    List<Book> findByAuthorContains(@Param("author") String author);

    /**
     * 重写方法，设置exported=false，用来隐藏方法
     *
     * @param iterable
     * @return
     */
    @Override
    @RestResource(exported = false)
    List<Book> findAllById(Iterable<Integer> iterable);
}
