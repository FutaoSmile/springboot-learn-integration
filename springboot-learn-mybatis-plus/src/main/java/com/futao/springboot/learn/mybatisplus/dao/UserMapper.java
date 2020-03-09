package com.futao.springboot.learn.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.futao.springboot.learn.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author futao
 * @date 2020/3/9.
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 条件分页查询-条件来自mybatisPlus的条件构造器
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<User> selectByXmlPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    List<User> selectByXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
