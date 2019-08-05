package com.futao.springboot.learn.security.dao;

import com.futao.springboot.learn.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author futao
 * Created on 2019-07-23.
 */
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
}
