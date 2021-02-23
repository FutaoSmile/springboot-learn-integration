package com.futao.springboot.learn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.futao.springboot.learn.mybatisplus.entity.enums.UserGenderEnum;
import com.futao.springboot.learn.mybatisplus.typehandlers.EnumTypeHandler;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * @author futao
 * @date 2020/3/9.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("plus_user")
public class User extends IdTimeEntity<User> {

    @TableField(value = "full_name")
    private String fullName;

    @TableField(value = "age")
    private int age;

    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 性别
     */
    @TableField(typeHandler = EnumTypeHandler.class)
    private UserGenderEnum gender;

    /**
     * JDK8中读取文件的方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String path = "D:\\src\\backend\\springboot-learn-integration\\springboot-learn-mybatis-plus\\src\\main\\java\\com\\futao\\springboot\\learn\\mybatisplus\\MybatisPlusApplication.java";
        Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        lines.forEach(System.out::println);
    }
}
