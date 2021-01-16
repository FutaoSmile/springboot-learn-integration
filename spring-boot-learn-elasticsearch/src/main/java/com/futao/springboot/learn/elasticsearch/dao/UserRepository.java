package com.futao.springboot.learn.elasticsearch.dao;

import com.futao.springboot.learn.elasticsearch.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author ft
 * @date 2021/1/15
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

    /**
     * 通过username 模糊查询
     *
     * @param username 用户名
     * @return 用户列表
     */
    List<UserEntity> queryAllByUsername(String username);
}
