package com.futao.springboot.learn.mybatisplus.entity.enums;

/**
 * @author ft <futao@mysteel.com>
 * @date 2021/2/5
 */
public enum UserGenderEnum implements IEnum<Integer> {
    UN_KNOW(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final int gender;
    private final String description;

    UserGenderEnum(int gender, String description) {
        this.gender = gender;
        this.description = description;
    }

    @Override
    public Integer value() {
        return gender;
    }
}
