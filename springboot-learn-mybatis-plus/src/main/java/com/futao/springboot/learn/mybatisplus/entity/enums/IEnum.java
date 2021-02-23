package com.futao.springboot.learn.mybatisplus.entity.enums;

/**
 * 系统内枚举需要实现的接口
 * 作用：便于在对Enum类型持久化和序列化时的处理
 *
 * @author ft <futao@mysteel.com>
 * @date 2021/2/5
 */
public interface IEnum<T> {
    /**
     * 枚举存储到DB中的值
     *
     * @return
     */
    T value();
}
