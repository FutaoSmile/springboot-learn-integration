package com.futao.springboot.learn.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JPA CJava Persistence APD 和Spring Data 是两个范畴的概念。
 * 作为一名Java EE 工程师，基本都有听说过Hibernate 框架。Hibernate 是一个ORM 框架，而
 * JPA 则是一种ORM 规范， JPA 和Hibernate 的关系就像JDB C 与JDB C 驱动的关系，即JPA 制定
 * 了ORM 规范， 而Hibernate 是这些规范的实现（事实上， 是先有Hibernate 后有JPA, JPA 规范的
 * 起草者也是Hibernate 的作者） ， 因此从功能上来说， JPA 相当于H ibernate 的一个子集。
 * Spring Data 是Spring 的一个子项目， 致力于简化数据库访问，通过规范的方法名称来分析开
 * 发者的意图，进而减少数据库访问层的代码量。Spring Data 不仅支持关系型数据库，也支持非关
 * 系型数据库。Spring Data JPA 可以有效简化关系型数据库访问代码。
 *
 * @author futao
 * Created on 2019-07-01.
 */
//@EntityScan("com.futao.springboot.learn.jpa.model")
//@EnableJpaRepositories(basePackages = "com.futao.springboot.learn.jpa.dao")
@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
