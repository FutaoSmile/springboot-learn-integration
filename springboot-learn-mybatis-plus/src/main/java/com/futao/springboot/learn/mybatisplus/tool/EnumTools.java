package com.futao.springboot.learn.mybatisplus.tool;

import com.futao.springboot.learn.mybatisplus.entity.enums.IEnum;
import com.futao.springboot.learn.mybatisplus.entity.enums.UserGenderEnum;

/**
 * 枚举工具类
 *
 * @author ft <futao@mysteel.com>
 * @date 2021/2/5
 */
public class EnumTools {
    private EnumTools() {
    }

    /**
     *
     * @param value
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends IEnum<?>> T valueOf(Object value, Class<T> tClass) {
        if (value == null || tClass == null) {
            return null;
        }
        for (IEnum enumConstant : tClass.getEnumConstants()) {
            if (enumConstant.value().equals(value)) {
                return (T) enumConstant;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        UserGenderEnum userGenderEnum = EnumTools.valueOf(1, UserGenderEnum.class);
        System.out.println(userGenderEnum);
    }
}
