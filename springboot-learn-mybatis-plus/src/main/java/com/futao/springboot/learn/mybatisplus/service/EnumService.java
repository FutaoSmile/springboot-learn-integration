package com.futao.springboot.learn.mybatisplus.service;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.futao.springboot.learn.mybatisplus.dao.UserMapper;
import com.futao.springboot.learn.mybatisplus.entity.User;
import com.futao.springboot.learn.mybatisplus.entity.enums.UserGenderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author ft <futao@mysteel.com>
 * @date 2021/2/5
 */
@Service
@Slf4j
public class EnumService implements ApplicationRunner {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private MybatisPlusProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        add();
    }

    public User add() {
        log.info("{}", properties.toString());
        User futao = new User("futao", 10, LocalDate.now(), UserGenderEnum.MALE);
        userMapper.insert(futao);
        log.info("{}", futao);


        User user = userMapper.selectById(futao.getId());
        log.info("{}", user);
        return futao;
    }

}
