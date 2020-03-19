package com.futao.springboot.learn.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.futao.springboot.learn.mybatisplus.dao.UserMapper;
import com.futao.springboot.learn.mybatisplus.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author futao
 * @date 2020/3/9.
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    public void insert(User user) {
        save(user);
    }

    public static void main(String[] args) {
        System.out.println(1 / 60D + (2 / 60D));
    }
}
